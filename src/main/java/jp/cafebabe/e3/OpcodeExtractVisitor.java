package jp.cafebabe.e3;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import jp.cafebabe.e3.exec.OpcodeManager;

public final class OpcodeExtractVisitor extends ClassVisitor{
    private static final String ECMANAGER = "jp/cafebabe/e3/exec/EntropyCounterManager";
    private static final String METHOD_DESC = "()V";

    private String className;

    public OpcodeExtractVisitor(final ClassVisitor visitor){
        super(Opcodes.ASM4, visitor);
    }

    public String getClassName(){
        return className;
    }

    @Override
    public void visit(final int version, final int access,
                      final String name, final String signature,
                      final String superName, final String[] interfaces){
        this.className = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name,
                                     final String desc, final String signature,
                                     final String[] exceptions){
        MethodVisitor visitor = super.visitMethod(
            access, name, desc, signature, exceptions
        );
        return new OpcodeExtractMethodVisitor(visitor, className, name);
    }

    private static final class OpcodeExtractMethodVisitor extends MethodVisitor{
        private String className;
        private String callee;

        public OpcodeExtractMethodVisitor(final MethodVisitor visitor,
                                          final String className,
                                          final String callee){
            super(Opcodes.ASM4, visitor);
            this.className = className;
            this.callee = callee;
        }

        @Override
        public void visitCode(){
            super.visitCode();
            super.visitLdcInsn(className);
            super.visitLdcInsn(callee);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER,
                "entryMethod", "(Ljava/lang/String;Ljava/lang/String;)V"
            );
        }

        @Override
        public void visitInsn(final int opcode){
            String methodName = OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );

            super.visitInsn(opcode);
        }

        @Override
        public void visitIntInsn(final int opcode, final int value){
            String methodName =
                OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitIntInsn(opcode, value);
        }

        @Override
        public void visitTypeInsn(final int opcode, final String value){
            String methodName =
                OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitTypeInsn(opcode, value);
        }

        @Override
        public void visitVarInsn(final int opcode, final int value){
            String methodName =
                OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitVarInsn(opcode, value);
        }

        @Override
        public void visitFieldInsn(final int opcode, final String owner,
                                   final String name, final String desc){
            String methodName =
                OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitFieldInsn(opcode, owner, name, desc);
        }

        @Override
        public void visitMethodInsn(final int opcode, final String owner,
                                    final String name, final String desc){
            String methodName =
                OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitMethodInsn(opcode, owner, name, desc);
        }

        @Override
        public void visitJumpInsn(final int opcode, final Label target){
            String methodName =
                OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitJumpInsn(opcode, target);
        }

        @Override
        public void visitLdcInsn(final Object value){
            String methodName =
                OpcodeManager.getInstance().getMethodName(Opcodes.LDC);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitLdcInsn(value);
        }

        @Override
        public void visitIincInsn(final int opcode, final int increment){
            String methodName =
                OpcodeManager.getInstance().getMethodName(opcode);
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitIincInsn(opcode, increment);
        }

        @Override
        public void visitTableSwitchInsn(final int from, final int to,
                                         final Label defaultLabel,
                                         final Label... targets){
            String methodName = OpcodeManager.getInstance().getMethodName(
                Opcodes.TABLESWITCH
            );
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitTableSwitchInsn(from, to, defaultLabel, targets);
        }

        @Override
        public void visitLookupSwitchInsn(final Label defaultLabel,
                                          final int[] values,
                                          final Label[] targets){
            String methodName = OpcodeManager.getInstance().getMethodName(
                Opcodes.LOOKUPSWITCH
            );
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitLookupSwitchInsn(defaultLabel, values, targets);
        }

        @Override
        public void visitMultiANewArrayInsn(final String desc, final int dim){
            String methodName = OpcodeManager.getInstance().getMethodName(
                Opcodes.MULTIANEWARRAY
            );
            super.visitMethodInsn(
                Opcodes.INVOKESTATIC, ECMANAGER, methodName, METHOD_DESC
            );
            super.visitMultiANewArrayInsn(desc, dim);
        }
    };
}
