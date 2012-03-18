package entropy;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class OpcodeExtractionTransformer implements ClassFileTransformer{
    public byte[] transform(ClassLoader loader, String className, Class type,
                            ProtectionDomain domain, byte[] originalData){
        DefaultTransformFilter filter = new DefaultTransformFilter();

        boolean flag = filter.filter(className);
        if(flag){
            ClassReader reader = new ClassReader(originalData);
            ClassWriter writer = new ClassWriter(
                ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS
            );

            OpcodeExtractVisitor visitor = new OpcodeExtractVisitor(writer);
            reader.accept(visitor, ClassReader.SKIP_DEBUG);

            byte[] transformedData = writer.toByteArray();
            output(className, transformedData);

            return transformedData;
        }
        return null;
    }

    private void output(String className, byte[] data){
        try{
            File file = new File("hoge", className + ".class");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            out.write(data);
            out.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}