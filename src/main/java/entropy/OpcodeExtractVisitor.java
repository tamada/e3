package entropy;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class OpcodeExtractVisitor extends ClassVisitor{
    private String className;

    public OpcodeExtractVisitor(ClassVisitor visitor){
        super(Opcodes.ASM4, visitor);
    }

    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces){
        this.className = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions){
        MethodVisitor visitor = super.visitMethod(access, name, desc, signature, exceptions);
        return new OpcodeExtractMethodVisitor(visitor, className, name);
    }

    private static class OpcodeExtractMethodVisitor extends MethodVisitor{
        private String className;
        private String methodName;

        public OpcodeExtractMethodVisitor(MethodVisitor visitor,
                                          String className, String methodName){
            super(Opcodes.ASM4, visitor);
            this.className = className;
            this.methodName = methodName;
        }

        @Override
        public void visitCode(){
            super.visitCode();
            super.visitLdcInsn(className);
            super.visitLdcInsn(methodName);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                "entryMethod", "(Ljava/lang/String;Ljava/lang/String;)V"
            );
        }

        @Override
        public void visitInsn(int opcode){
            if(opcode == Opcodes.RETURN  || opcode == Opcodes.IRETURN ||
               opcode == Opcodes.LRETURN || opcode == Opcodes.FRETURN ||
               opcode == Opcodes.DRETURN || opcode == Opcodes.ARETURN){
                super.visitMethodInsn(
                    Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                    "returnMethod", "()V"
                );
            }
            String methodName = OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );

            super.visitInsn(opcode);
        }

        @Override
        public void visitIntInsn(int opcode, int value){
            String methodName = OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitIntInsn(opcode, value);
        }

        @Override
        public void visitTypeInsn(int opcode, String value){
            String methodName = OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitTypeInsn(opcode, value);
        }

        @Override
        public void visitVarInsn(int opcode, int value){
            String methodName = OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitVarInsn(opcode, value);
        }

        @Override
        public void visitFieldInsn(int opcode, String owner, String name, String desc){
            String methodName = OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitFieldInsn(opcode, owner, name, desc);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc){
            String methodName = OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitMethodInsn(opcode, owner, name, desc);
        }

        @Override
        public void visitJumpInsn(int opcode, Label target){
            String methodName = OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitJumpInsn(opcode, target);
        }

        @Override
        public void visitLdcInsn(Object value){
            String methodName = OpcodeManager.getInstance().getMethodName(Opcodes.LDC);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitLdcInsn(value);
        }

        @Override
        public void visitIincInsn(int opcode, int increment){
            String methodName = OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitIincInsn(opcode, increment);
        }

        @Override
        public void visitTableSwitchInsn(int from, int to, Label defaultLabel, Label[] targets){
            String methodName = OpcodeManager.getInstance().getMethodName(Opcodes.TABLESWITCH);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitTableSwitchInsn(from, to, defaultLabel, targets);
        }

        @Override
        public void visitLookupSwitchInsn(Label defaultLabel, int[] values, Label[] targets){
            String methodName = OpcodeManager.getInstance().getMethodName(Opcodes.LOOKUPSWITCH);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitLookupSwitchInsn(defaultLabel, values, targets);
        }

        @Override
        public void visitMultiANewArrayInsn(String desc, int dim){
            String methodName = OpcodeManager.getInstance().getMethodName(Opcodes.MULTIANEWARRAY);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, "entropy/EntropyCounterManager",
                methodName, "()V"
            );
            super.visitMultiANewArrayInsn(desc, dim);
        }
    };
}