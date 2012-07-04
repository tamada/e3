package jp.cafebabe.e3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class E3ClassLoader extends ClassLoader{
    private OpcodeExtractionTransformer transformer;

    public E3ClassLoader(ClassLoader parent, OpcodeExtractionTransformer transformer){
        super(parent);
        this.transformer = transformer;
    }

    public E3ClassLoader(OpcodeExtractionTransformer transformer){
        super();
        this.transformer = transformer;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException{
        Class<?> clazz = super.loadClass(name);
        clazz = transform(clazz, name);

        return clazz;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException{
        Class<?> clazz = super.findClass(name);
        clazz = transform(clazz, name);

        return clazz;
    }

    private Class<?> transform(Class<?> clazz, String name){
        if(!transformer.isTarget(name)){
            return clazz;
        }
        Class<?> transformedClass = null;
        byte[] originalData = readOriginalData(clazz);
        if(originalData != null){
            byte[] transformedData = transformer.transform(name, originalData);

            Class<?> newClazz = defineClass(name, transformedData, 0, transformedData.length);
            if(newClazz != null){
                transformedClass = newClazz;
            }
        }
        if(transformedClass == null){
            transformedClass = clazz;
        }
        return transformedClass;
    }

    private byte[] readOriginalData(Class<?> clazz){
        InputStream in = null;
        try{
            // hoge.ClassName -> /hoge/ClassName.class
            URL url = clazz.getResource("/" + clazz.getName().replace('.', '/') + ".class");
            in = url.openStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            int data;
            while((data = in.read()) != -1){
                bout.write(data);
            }
            byte[] originalData = bout.toByteArray();
            bout.close();

            return originalData;            
        } catch(IOException e){
            
        } finally{
            if(in != null){
                try{
                    in.close();
                } catch(IOException e){
                }
            }
        }
        return null;
    }
}
