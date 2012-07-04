package jp.cafebabe.e3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.cafebabe.e3.exec.result.MultipleResultSet;
import jp.cafebabe.e3.exec.result.ResultSet;

/**
 * This class perform to transform given classes for extracting/calculating
 * runtime entropy. For extracting runtime entropy, this class weaves extracting
 * method calls to each opcode. Resultant program can extract runtime opcode
 * list and calculate entropy. Note that, resultant program requires the library
 * of this class.
 * 
 * @author Haruaki Tamada
 */
public class Main{
    public static enum Operation{
        TRANSFORM, CALCULATE, EXECUTE,
    };

    private static final int BUFFER_SIZE = 256;
    private String dest = ".";
    private String filterFile = null;
    private Operation operation = null;
    private MultipleResultSet mrs;

    /**
     * Constructor.
     * 
     * @param args
     *            program arguments
     */
    public Main(final String[] args) throws IOException{
        String[] arguments = parseOptions(args);

        TransformFilter filter = null;
        if(filterFile != null){
            filter = new CsvTransformFilter(filterFile);
        }
        OpcodeExtractionTransformer transformer = new OpcodeExtractionTransformer(
                filter);

        if(operation == Operation.EXECUTE){
            executeArgument(arguments, transformer);
        }
        else{
            performArguments(arguments, transformer);
        }
    }

    private void executeArgument(String[] args, OpcodeExtractionTransformer transformer){

        try{
            if(args[0].endsWith(".jar")){
                executeJar(transformer, args);
            }
            else{
                executeClass(transformer, args);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void executeJar(OpcodeExtractionTransformer transformer, String[] args)
            throws ClassNotFoundException, SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException{

        File file = new File(args[0]);
        URLClassLoader urlLoader = new URLClassLoader(new URL[] { file.toURI().toURL(), });
        E3ClassLoader e3Loader = new E3ClassLoader(urlLoader, transformer);

        JarFile jarfile = new JarFile(file);
        Manifest manifest = jarfile.getManifest();

        String mainClass = manifest.getMainAttributes().getValue("Main-Class");
        Class<?> clazz = e3Loader.loadClass(mainClass);
        Method method = clazz.getMethod("main", String[].class);
        String[] arguments = shiftArgs(args);

        method.invoke(null, new Object[] { arguments, });
    }

    private void executeClass(OpcodeExtractionTransformer transformer, String[] args)
            throws ClassNotFoundException, SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException{
        E3ClassLoader loader = new E3ClassLoader(transformer);
        Class<?> clazz = loader.loadClass(args[0]);
        Method method = clazz.getMethod("main", String[].class);
        String[] arguments = shiftArgs(args);

        method.invoke(null, new Object[] { arguments, });
    }

    private String[] shiftArgs(String[] originalArgs){
        String[] args = new String[originalArgs.length - 1];
        for(int i = 0; i < args.length; i++){
            args[i] = originalArgs[i + 1];
        }
        return args;
    }

    private void performArguments(String[] arguments,
            OpcodeExtractionTransformer transformer)
            throws UnsupportedEncodingException{
        for(String file: arguments){
            try{
                if(file.endsWith(".jar")){
                    performJar(transformer, file);
                }
                else{
                    perform(transformer, file);
                }
            } catch(IOException e){
                Logger.getLogger(getClass().getName()).log(Level.WARNING,
                        e.getMessage(), e);
            }
        }
        if(operation == Operation.CALCULATE){
            mrs.print(new PrintWriter(new OutputStreamWriter(System.out, "utf-8")));
        }
    }

    private void performJar(final OpcodeExtractionTransformer transformer,
            final String jarFile) throws IOException{
        JarFile jar = new JarFile(jarFile);
        for(Enumeration<JarEntry> e = jar.entries(); e.hasMoreElements();){
            JarEntry entry = e.nextElement();
            String name = entry.getName();
            if(name.endsWith(".class")){
                String className = name.substring(0,
                        name.length() - ".class".length()).replace('/', '.');
                byte[] original = getData(jar, entry);

                if(operation == Operation.TRANSFORM){
                    transform(transformer, className, original);
                }
                else{
                    calculate(className, original);
                }
            }
        }
    }

    /**
     * performes transformation/calculation.
     * 
     * @param file
     *            byte code data of target class.
     * @throws IOException
     *             I/O error.
     */
    private void perform(final OpcodeExtractionTransformer transformer,
            final String file) throws IOException{
        byte[] original = getData(file);
        String className = ClassNameExtractVisitor.parseClassName(original);

        if(operation == Operation.TRANSFORM){
            transform(transformer, className, original);
        }
        else{
            calculate(className, original);
        }
    }

    private void calculate(String className, byte[] data)
            throws UnsupportedEncodingException{
        if(mrs == null){
            mrs = new MultipleResultSet();
        }
        ResultSet rs = StaticallyOpcodeExtractVisitor.parse(data);
        mrs.add(rs);
    }

    private void transform(final OpcodeExtractionTransformer transformer,
            final String className, final byte[] data){
        byte[] transformed = transformer.transform(className, data);
        if(transformed == null){
            transformed = data;
        }
        transformer.output(dest, className, transformed);
    }

    private byte[] getData(final JarFile file, JarEntry entry)
            throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = null;
        try{
            in = file.getInputStream(entry);
            byte[] buffer = new byte[BUFFER_SIZE];
            int read;
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
            out.close();
            return out.toByteArray();
        } finally{
            if(in != null){
                in.close();
            }
        }
    }

    private byte[] getData(final String file) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = null;
        try{
            in = new FileInputStream(file);
            int read;
            byte[] buffer = new byte[BUFFER_SIZE];
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
            out.close();
            return out.toByteArray();
        } finally{
            if(in != null){
                in.close();
            }
        }
    }

    private String[] parseOptions(final String[] args){
        List<String> list = new ArrayList<String>();
        boolean exitFlag = false;
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-d") || args[i].equals("--dest")){
                if(i <= args.length){
                    dest = args[i + 1];
                    i++;
                }
            }
            else if(args[i].equals("-c") || args[i].equals("--calculate")){
                if(operation == null){
                    operation = Operation.CALCULATE;
                }
            }
            else if(args[i].equals("-t") || args[i].equals("--transformed")){
                operation = Operation.TRANSFORM;
            }
            else if(args[i].equals("-e") || args[i].equals("--execute")){
                operation = Operation.EXECUTE;
            }
            else if(args[i].equals("-f") || args[i].equals("--filter")){
                if(i <= args.length){
                    filterFile = args[i + 1];
                    i++;
                }
            }
            else if(args[i].equals("-h") || args[i].equals("--help")){
                showHelp();
                exitFlag = true;
            }
            else{
                list.add(args[i]);
            }
        }
        String[] arguments = list.toArray(new String[list.size()]);
        if(!exitFlag && arguments.length == 0){
            showHelp();
        }
        if(exitFlag){
            arguments = new String[0];
        }
        if(operation == null){
            operation = Operation.CALCULATE;
        }
        return arguments;
    }

    private void showHelp(){
        String ln = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append(
                "java -jar e3-1.1-SNAPSHOT.jar [EXEC_OPTION] [OPTIONS] <TARGETS...>")
                .append(ln);
        sb.append(ln);
        sb.append("EXEC_OPTIONS").append(ln);
        sb.append(
                "  -c, --calculate:       specify to calculate entropy of given Java class")
                .append(ln);
        sb.append(
                "                         files statically.  In this option, ``dest'' option")
                .append(ln);
        sb.append(
                "                         is ignored.  This option is default.")
                .append(ln);
        sb.append(
                "  -e, --execute <class>: Execute main method of given class.")
                .append(ln);
        sb.append(
                "  -t, --transform:       specify to transform Java class files for counting")
                .append(ln);
        sb.append(
                "                         entropy.  If this option is specified, ``calculate''")
                .append(ln);
        sb.append("                         option is ignored.").append(ln);
        sb.append("OPTIONS").append(ln);
        sb.append(
                "  -d, --dest <dest>:     specify destination.  Default is current directory.")
                .append(ln);
        sb.append(
                "  -f, --filter <filter>: specify filter for processing targets.")
                .append(ln);
        sb.append("                         Default filter is prescripted.")
                .append(ln);
        sb.append("  -h, --help:            show this message.").append(ln);
        sb.append(ln);
        sb.append("TARGETS").append(ln);
        sb.append("  Accept Java class files and jar files.");
        System.out.println(new String(sb));
    }

    /**
     * main method.
     */
    public static void main(final String[] args) throws IOException{
        new Main(args);
    }
}