package jp.cafebabe.e3;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * <p>
 * This visitor class extracts class name from bytecode.  Extracted
 * name can obtain by {@link #getClassName <code>getClassName</code>}
 * method after calling <code>accept</code> method of
 * <code>ClassReader</code>.
 * </p><p>
 * For more simply use, {@link #parseClassName
 * <code>parseClassName</code>} method parses bytecode and returns
 * aquired class name.
 * </p>
 * @author Haruaki Tamada
 */
public class ClassNameExtractVisitor extends ClassVisitor{
    private String className;

    public ClassNameExtractVisitor(ClassVisitor visitor){
        super(Opcodes.ASM4, visitor);
    }

    public ClassNameExtractVisitor(){
        super(Opcodes.ASM4);
    }

    public String getClassName(){
        return className;
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces){
        this.className = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    public static String parseClassName(byte[] data){
        ClassReader reader = new ClassReader(data);
        ClassNameExtractVisitor visitor = new ClassNameExtractVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        return visitor.getClassName();
    }
}
