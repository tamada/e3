package jp.cafebabe.e3;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import jp.cafebabe.e3.exec.result.ResultSet;

/**
 * This class perform to transform given classes for
 * extracting/calculating runtime entropy.  For extracting runtime
 * entropy, this class weaves extracting method calls to each opcode.
 * Resultant program can extract runtime opcode list and calculate
 * entropy.  Note that, resultant program requires the library of this
 * class.
 *
 * @author Haruaki Tamada
 */
public class Main{
    public static enum Operation{
        TRANSFORM, CALCULATE,
    };

    private static final int BUFFER_SIZE = 256;
    private String dest = ".";
    private String filterFile = null;
    private Operation operation = null;

    /**
     * Constructor.
     * @param args program arguments
     */
    public Main(final String[] args) throws IOException{
        String[] arguments = parseOptions(args);

        TransformFilter filter = null;
        if(filterFile != null){
            filter = new CsvTransformFilter(filterFile);
        }
        OpcodeExtractionTransformer transformer = new OpcodeExtractionTransformer(filter);

        for(String file: arguments){
            try{
                if(file.endsWith(".jar")){
                    performJar(transformer, file);
                }
                else{
                    perform(transformer, file);
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void performJar(final OpcodeExtractionTransformer transformer,
                            final String jarFile) throws IOException{
        JarFile jar = new JarFile(jarFile);
        for(Enumeration<JarEntry> e = jar.entries(); e.hasMoreElements(); ){
            JarEntry entry = e.nextElement();
            String name = entry.getName();
            if(name.endsWith(".class")){
                String className = name.substring(0, name.length() - ".class".length()).replace('/', '.');
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
     * performes transformation.
     * @param file transformation target class.
     * @throws IOException I/O error.
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

    private void calculate(String className, byte[] data){
        ResultSet rs = StaticallyOpcodeExtractVisitor.parse(data);
        rs.print(new PrintWriter(System.out));
    }

    private void transform(final OpcodeExtractionTransformer transformer, 
                           final String className, final byte[] data){
        byte[] transformed = transformer.transform(className, data);
        if(transformed == null){
            transformed = data;
        }
        transformer.output(dest, className, transformed);
    }

    private byte[] getData(final JarFile file, JarEntry entry) throws IOException{
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
        }
        finally{
            if(in != null){
                try{
                    in.close();
                }
                catch(IOException e){
                    throw new InternalError(e.getMessage());
                }
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
        }
        finally{
            if(in != null){
                try{
                    in.close();
                }
                catch(IOException e){
                    throw new InternalError(e.getMessage());
                }
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
        sb.append("java -jar e3-1.1-SNAPSHOT.jar [OPTIONS] <TARGETS...>").append(ln);
        sb.append(ln);
        sb.append("OPTIONS").append(ln);
        sb.append("  -c, --calculate:       specify to calculate entropy of given Java class").append(ln);
        sb.append("                         files statically.  In this option, ``dest'' option").append(ln);
        sb.append("                         is ignored.  This option is default.").append(ln);
        sb.append("  -t, --transform:       specify to transform Java class files for counting").append(ln);
        sb.append("                         entropy.  If this option is specified, ``calculate''").append(ln);
        sb.append("                         option is ignored.").append(ln);
        sb.append("  -d, --dest <dest>:     specify destination.  Default is current directory.").append(ln);
        sb.append("  -f, --filter <filter>: specify filter for processing targets.").append(ln);
        sb.append("                         Default filter is prescripted.").append(ln);
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