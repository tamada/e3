package jp.cafebabe.e3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Launcher{
    private String[] arguments;
    private OpcodeExtractionTransformer transformer;

    public Launcher(OpcodeExtractionTransformer transformer, String[] arguments){
        this.arguments = new String[arguments.length];
        System.arraycopy(arguments, 0, this.arguments, 0, arguments.length);
        this.transformer = transformer;
    }

    public void perform(){
        try{
            if(arguments[0].endsWith(".jar")){
                executeJar();
            }
            else if(arguments[0].endsWith(".class")){
                executeClassFile();
            }
            else{
                executeClass();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void executeJar() throws ClassNotFoundException, SecurityException,
            NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException, IOException{

        File file = new File(arguments[0]);
        ClassLoader e3Loader = createClassLoader(file.toURI().toURL());

        JarFile jarfile = new JarFile(file);
        Manifest manifest = jarfile.getManifest();

        String mainClass = manifest.getMainAttributes().getValue("Main-Class");

        execute(e3Loader, mainClass, arguments);
    }

    private void executeClassFile() throws IOException, ClassNotFoundException,
            SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException{
        byte[] data = readFully(new File(arguments[0]));
        String className = ClassNameExtractVisitor.parseClassName(data);
        String subPath = className.replace('.', '/') + ".class";

        File classPath = null;
        if(arguments[0].contains(subPath)){
            String classPathString = arguments[0].substring(0,
                    arguments[0].indexOf(subPath));
            classPath = new File(classPathString);
        }
        ClassLoader loader = createClassLoader(classPath.toURI().toURL());

        execute(loader, className, arguments);
    }

    private void executeClass() throws ClassNotFoundException,
            SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException{
        ClassLoader loader = createClassLoader();

        execute(loader, arguments[0], arguments);
    }

    private void execute(ClassLoader loader, String mainClass, String[] arguments) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
        Class<?> clazz = loader.loadClass(mainClass);
        Method method = clazz.getMethod("main", String[].class);

        String[] args = shiftArgs(arguments);
        method.invoke(null, new Object[] { args, });
    }

    private ClassLoader createClassLoader(){
        return new E3ClassLoader(transformer);
    }

    private ClassLoader createClassLoader(URL url){
        URLClassLoader urlLoader = new URLClassLoader(new URL[] { url, });
        return new E3ClassLoader(urlLoader, transformer);
    }

    private String[] shiftArgs(String[] originalArgs){
        String[] args = new String[originalArgs.length - 1];
        for(int i = 0; i < args.length; i++){
            args[i] = originalArgs[i + 1];
        }
        return args;
    }

    private byte[] readFully(File file) throws IOException{
        InputStream in = null;
        try{
            in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int read;
            while((read = in.read()) != -1){
                out.write(read);
            }
            out.close();
            return out.toByteArray();
        } finally{
            if(in != null){
                in.close();
            }
        }
    }
}
