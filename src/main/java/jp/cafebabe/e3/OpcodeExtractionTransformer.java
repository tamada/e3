package jp.cafebabe.e3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public final class OpcodeExtractionTransformer implements ClassFileTransformer{
    private String currentClassName;

    @Override
    public byte[] transform(final ClassLoader loader, final String className,
                            final Class<?> type, final ProtectionDomain domain,
                            final byte[] originalData){
        TransformFilter filter = new DefaultTransformFilter();

        boolean flag = filter.filter(className);
        this.currentClassName = className;
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

    public byte[] transform(final byte[] originalData){
        ClassReader reader = new ClassReader(originalData);
        ClassWriter writer = new ClassWriter(
            ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS
        );
        OpcodeExtractVisitor visitor = new OpcodeExtractVisitor(writer);
        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        this.currentClassName = visitor.getClassName();

        return writer.toByteArray();
    }

    public String getClassName(){
        return currentClassName;
    }

    public void output(final String dest, final byte[] data){
        try{
            File file = new File(dest, currentClassName + ".class");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            out.write(data);
            out.close();
        }
        catch(IOException e){
            throw new InternalError(e.getMessage());
        }
    }
}