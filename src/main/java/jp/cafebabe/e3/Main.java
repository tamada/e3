package jp.cafebabe.e3;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    private static final int BUFFER_SIZE = 256;
    private String dest = ".";
    private String filterFile = null;

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
                perform(transformer, file);
            }
            catch(IOException e){
                e.printStackTrace();
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

        byte[] transformed = transformer.transform(className, original);
        if(transformed == null){
            transformed = original;
        }
        transformer.output(dest, className, transformed);
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
        if(arguments.length == 0){
            showHelp();
        }
        if(exitFlag){
            arguments = new String[0];
        }
        return arguments;
    }

    private void showHelp(){
        String ln = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("java -jar e3-1.1-SNAPSHOT.jar [OPTIONS] <TARGETS...>").append(ln);
        sb.append(ln);
        sb.append("OPTIONS").append(ln);
        sb.append("  -d, --dest <dest>:     specify destination.").append(ln);
        sb.append("                         Default is current directory.").append(ln);
        sb.append("  -f, --filter <filter>: specify transformation filter.").append(ln);
        sb.append("                         Default filter is prescripted.").append(ln);
        sb.append("  -h, --help:            show this message.").append(ln);
        sb.append(ln);
        sb.append("TARGETS").append(ln);
        sb.append("  only accept Java class files.");
        System.out.println(new String(sb));
    }

    /**
     * main method.
     */
    public static void main(final String[] args) throws IOException{
        new Main(args);
    }
}