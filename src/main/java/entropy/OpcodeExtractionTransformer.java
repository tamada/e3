package entropy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class OpcodeExtractionTransformer implements ClassFileTransformer{
    private String className;

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> type,
                            ProtectionDomain domain, byte[] originalData){
        DefaultTransformFilter filter = new DefaultTransformFilter();

        boolean flag = filter.filter(className);
        this.className = className;
        if(flag){
            ClassReader reader = new ClassReader(originalData);
            ClassWriter writer = new ClassWriter(
                ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS
            );
            OpcodeExtractVisitor visitor = new OpcodeExtractVisitor(writer);
            reader.accept(visitor, ClassReader.SKIP_DEBUG);

            byte[] transformedData = writer.toByteArray();

            return transformedData;
        }
        return null;
    }

    public byte[] transform(byte[] originalData){
        ClassReader reader = new ClassReader(originalData);
        ClassWriter writer = new ClassWriter(
            ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS
        );
        OpcodeExtractVisitor visitor = new OpcodeExtractVisitor(writer);
        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        this.className = visitor.getClassName();

        return writer.toByteArray();
    }

    public String getClassName(){
        return className;
    }

    public void output(String dest, byte[] data){
        try{
            File file = new File(dest, className + ".class");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            out.write(data);
            out.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}