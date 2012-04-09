package jp.cafebabe.e3;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main{
    private String dest = ".";

    public Main(String[] args){
        String[] arguments = parseOptions(args);

        for(String file: arguments){
            try{
                perform(file);
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void perform(String file) throws IOException{
        byte[] original = getData(file);

        OpcodeExtractionTransformer transformer = new OpcodeExtractionTransformer();
        byte[] transformed = transformer.transform(original);
        transformer.output(dest, transformed);
    }

    private byte[] getData(String file) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = null;
        try{
            in = new FileInputStream(file);
            int read;
            byte[] buffer = new byte[256];
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }
            out.close();
            return out.toByteArray();
        } finally{
            if(in != null){
                try{
                    in.close();
                } catch(IOException e){
                    throw new InternalError(e.getMessage());
                }
            }
        }
    }

    private String[] parseOptions(String[] args){
        List<String> list = new ArrayList<String>();
        boolean exitFlag = false;
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-d") || args[i].equals("--dest")){
                if(i <= args.length){
                    dest = args[i + 1];
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
        System.out.println("java -jar e3-1.0.jar [OPTIONS] <TARGETS...>");
        System.out.println("");
        System.out.println("OPTIONS");
        System.out.println("  -d, --dest <dest>: set output destination. Default is current directory.");
        System.out.println("  -h, --help:        show this message");
        System.out.println("");
        System.out.println("TARGETS");
        System.out.println("  only accept Java class files.");
    }

    public static void main(String[] args){
        new Main(args);
    }
}
