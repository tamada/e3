package jp.cafebabe.e3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * The implementation class of <code>ClassFileTransformer</code>
 * interface.  Actual transformation is provided {@link
 * OpcodeExtractVisitor <code>OpcodeExtractVisitor</code>} class.
 *
 * @author Haruaki Tamada
 */
public final class OpcodeExtractionTransformer implements ClassFileTransformer{
    private TransformFilter filter;

    public OpcodeExtractionTransformer(){
        this(new DefaultTransformFilter());
    }

    public boolean isTarget(String name){
        return filter.isTarget(name);
    }

    public OpcodeExtractionTransformer(TransformFilter filter){
        this.filter = filter;
        if(filter == null){
            this.filter = new DefaultTransformFilter();
        }
    }

    /**
     * transforms given class file for extracting runtime opcode
     * sequence by weaving extracting code.
     */
    @Override
    public byte[] transform(final ClassLoader loader, final String className,
                            final Class<?> type, final ProtectionDomain domain,
                            final byte[] originalData){
        return transform(className, originalData, loader);
    }

    public byte[] transform(final String className, final byte[] originalData){
        return transform(className, originalData, getClass().getClassLoader());
    }

    public byte[] transform(final String className, final byte[] originalData, ClassLoader loader){
        if(filter.isTarget(className)){
            ClassReader reader = new ClassReader(originalData);
            ClassWriter writer = new E3ClassWriter(
                ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS,
                loader
            );
            OpcodeExtractVisitor visitor = new OpcodeExtractVisitor(writer);
            reader.accept(visitor, 0);

            return writer.toByteArray();
        }
        return null;
    }

    public void output(final String dest, String className, final byte[] data){
        try{
            File file = new File(dest, className + ".class");
            boolean flag = file.getParentFile().mkdirs();
            if(flag){
                FileOutputStream out = new FileOutputStream(file);
                out.write(data);
                out.close();
            }
        }
        catch(IOException e){
            throw new InternalError(e.getMessage());
        }
    }
}