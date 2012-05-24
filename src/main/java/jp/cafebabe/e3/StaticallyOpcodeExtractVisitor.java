package jp.cafebabe.e3;

import java.util.ArrayList;
import java.util.List;

import jp.cafebabe.e3.exec.EntropyCounter;
import jp.cafebabe.e3.exec.MethodEntropyCounter;
import jp.cafebabe.e3.exec.result.ResultSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Opcode extract visitor for static analysis.
 * For easy use, this class shows {@link #parse <code>parse</code>} method.
 * 
 * @author Haruaki Tamada
 */
public class StaticallyOpcodeExtractVisitor extends ClassVisitor{
    private List<EntropyCounter> list = new ArrayList<EntropyCounter>();
    private String className;

    public StaticallyOpcodeExtractVisitor(){
        super(Opcodes.ASM4);
    }

    public StaticallyOpcodeExtractVisitor(ClassVisitor visitor){
        super(Opcodes.ASM4, visitor);
    }

    public ResultSet getResultSet(){
        return new ResultSet(list);
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

        OpcodeExtractMethodVisitor mv = new OpcodeExtractMethodVisitor(visitor, className, name);
        list.add(mv.counter);
        return mv;
    }

    public static ResultSet parse(byte[] data){
        ClassReader reader = new ClassReader(data);
        StaticallyOpcodeExtractVisitor visitor = new StaticallyOpcodeExtractVisitor();
        reader.accept(visitor, 0);

        return visitor.getResultSet();
    }

    private static final class OpcodeExtractMethodVisitor extends MethodVisitor{
        private MethodEntropyCounter counter;

        public OpcodeExtractMethodVisitor(final MethodVisitor visitor,
                                          final String className,
                                          final String callee){
            super(Opcodes.ASM4, visitor);
            counter = new MethodEntropyCounter(className, callee);
        }

        @Override
        public void visitFieldInsn(int arg0, String arg1, String arg2, String arg3){
            if(arg0 == Opcodes.GETFIELD){
                counter.getfield();
            }
            else if(arg0 == Opcodes.PUTFIELD){
                counter.putfield();
            }
            else if(arg0 == Opcodes.GETSTATIC){
                counter.getstatic();
            }
            else if(arg0 == Opcodes.PUTSTATIC){
                counter.putstatic();
            }
            super.visitFieldInsn(arg0, arg1, arg2, arg3);            
        }

        @Override
        public void visitIincInsn(int arg0, int arg1){
            counter.iinc();
            super.visitIincInsn(arg0, arg1);
        }

        @Override
        public void visitInsn(int opcode){
            if(opcode == Opcodes.NOP){
                counter.nop();
            }
            else if(opcode == Opcodes.ACONST_NULL){
                counter.aconstNull();
            }
            else if(opcode == Opcodes.ICONST_M1){
                counter.iconstM1();
            }
            else if(opcode == Opcodes.ICONST_0){
                counter.iconst0();
            }
            else if(opcode == Opcodes.ICONST_1){
                counter.iconst1();
            }
            else if(opcode == Opcodes.ICONST_2){
                counter.iconst2();
            }
            else if(opcode == Opcodes.ICONST_3){
                counter.iconst3();
            }
            else if(opcode == Opcodes.ICONST_4){
                counter.iconst4();
            }
            else if(opcode == Opcodes.ICONST_5){
                counter.iconst5();
            }
            else if(opcode == Opcodes.LCONST_0){
                counter.lconst0();
            }
            else if(opcode == Opcodes.LCONST_1){
                counter.lconst1();
            }
            else if(opcode == Opcodes.FCONST_0){
                counter.fconst0();
            }
            else if(opcode == Opcodes.FCONST_1){
                counter.fconst1();
            }
            else if(opcode == Opcodes.DCONST_0){
                counter.dconst0();
            }
            else if(opcode == Opcodes.DCONST_1){
                counter.dconst1();
            }
            else if(opcode == Opcodes.IALOAD){
                counter.iaload();
            }
            else if(opcode == Opcodes.LALOAD){
                counter.laload();
            }
            else if(opcode == Opcodes.FALOAD){
                counter.faload();
            }
            else if(opcode == Opcodes.DALOAD){
                counter.daload();
            }
            else if(opcode == Opcodes.AALOAD){
                counter.aaload();
            }
            else if(opcode == Opcodes.BALOAD){
                counter.baload();
            }
            else if(opcode == Opcodes.CALOAD){
                counter.caload();
            }
            else if(opcode == Opcodes.SALOAD){
                counter.saload();
            }
            else if(opcode == Opcodes.IASTORE){
                counter.iastore();
            }
            else if(opcode == Opcodes.LASTORE){
                counter.lastore();
            }
            else if(opcode == Opcodes.FASTORE){
                counter.fastore();
            }
            else if(opcode == Opcodes.DASTORE){
                counter.dastore();
            }
            else if(opcode == Opcodes.AASTORE){
                counter.aastore();
            }
            else if(opcode == Opcodes.BASTORE){
                counter.bastore();
            }
            else if(opcode == Opcodes.CASTORE){
                counter.castore();
            }
            else if(opcode == Opcodes.SASTORE){
                counter.sastore();
            }
            else if(opcode == Opcodes.POP){
                counter.pop();
            }
            else if(opcode == Opcodes.POP2){
                counter.pop2();
            }
            else if(opcode == Opcodes.DUP){
                counter.dup();
            }
            else if(opcode == Opcodes.DUP_X1){
                counter.dupX1();
            }
            else if(opcode == Opcodes.DUP_X2){
                counter.dupX2();
            }
            else if(opcode == Opcodes.DUP2){
                counter.dup2();
            }
            else if(opcode == Opcodes.DUP2_X1){
                counter.dup2X1();
            }
            else if(opcode == Opcodes.DUP2_X2){
                counter.dup2X2();
            }
            else if(opcode == Opcodes.SWAP){
                counter.swap();
            }
            else if(opcode == Opcodes.IADD){
                counter.iadd();
            }
            else if(opcode == Opcodes.LADD){
                counter.ladd();
            }
            else if(opcode == Opcodes.FADD){
                counter.fadd();
            }
            else if(opcode == Opcodes.DADD){
                counter.dadd();
            }
            else if(opcode == Opcodes.ISUB){
                counter.isub();
            }
            else if(opcode == Opcodes.LSUB){
                counter.lsub();
            }
            else if(opcode == Opcodes.FSUB){
                counter.fsub();
            }
            else if(opcode == Opcodes.DSUB){
                counter.dsub();
            }
            else if(opcode == Opcodes.IMUL){
                counter.imul();
            }
            else if(opcode == Opcodes.LMUL){
                counter.lmul();
            }
            else if(opcode == Opcodes.FMUL){
                counter.fmul();
            }
            else if(opcode == Opcodes.DMUL){
                counter.dmul();
            }
            else if(opcode == Opcodes.IDIV){
                counter.idiv();
            }
            else if(opcode == Opcodes.LDIV){
                counter.ldiv();
            }
            else if(opcode == Opcodes.FDIV){
                counter.fdiv();
            }
            else if(opcode == Opcodes.DDIV){
                counter.ddiv();
            }
            else if(opcode == Opcodes.IREM){
                counter.irem();
            }
            else if(opcode == Opcodes.LREM){
                counter.lrem();
            }
            else if(opcode == Opcodes.FREM){
                counter.frem();
            }
            else if(opcode == Opcodes.DREM){
                counter.drem();
            }
            else if(opcode == Opcodes.INEG){
                counter.ineg();
            }
            else if(opcode == Opcodes.LNEG){
                counter.lneg();
            }
            else if(opcode == Opcodes.FNEG){
                counter.fneg();
            }
            else if(opcode == Opcodes.DNEG){
                counter.dneg();
            }
            else if(opcode == Opcodes.ISHL){
                counter.ishl();
            }
            else if(opcode == Opcodes.LSHL){
                counter.lshl();
            }
            else if(opcode == Opcodes.ISHR){
                counter.ishr();
            }
            else if(opcode == Opcodes.LSHR){
                counter.lshr();
            }
            else if(opcode == Opcodes.IUSHR){
                counter.iushr();
            }
            else if(opcode == Opcodes.LUSHR){
                counter.lushr();
            }
            else if(opcode == Opcodes.IAND){
                counter.iand();
            }
            else if(opcode == Opcodes.LAND){
                counter.land();
            }
            else if(opcode == Opcodes.IOR){
                counter.ior();
            }
            else if(opcode == Opcodes.LOR){
                counter.lor();
            }
            else if(opcode == Opcodes.IXOR){
                counter.ixor();
            }
            else if(opcode == Opcodes.LXOR){
                counter.lxor();
            }
            else if(opcode == Opcodes.I2L){
                counter.i2l();
            }
            else if(opcode == Opcodes.I2F){
                counter.i2f();
            }
            else if(opcode == Opcodes.I2D){
                counter.i2d();
            }
            else if(opcode == Opcodes.L2I){
                counter.l2i();
            }
            else if(opcode == Opcodes.L2F){
                counter.l2f();
            }
            else if(opcode == Opcodes.L2D){
                counter.l2d();
            }
            else if(opcode == Opcodes.F2I){
                counter.f2i();
            }
            else if(opcode == Opcodes.F2L){
                counter.f2l();
            }
            else if(opcode == Opcodes.F2D){
                counter.f2d();
            }
            else if(opcode == Opcodes.D2I){
                counter.d2i();
            }
            else if(opcode == Opcodes.D2L){
                counter.d2l();
            }
            else if(opcode == Opcodes.D2F){
                counter.d2f();
            }
            else if(opcode == Opcodes.I2B){
                counter.i2b();
            }
            else if(opcode == Opcodes.I2C){
                counter.i2c();
            }
            else if(opcode == Opcodes.I2S){
                counter.i2s();
            }
            else if(opcode == Opcodes.LCMP){
                counter.lcmp();
            }
            else if(opcode == Opcodes.FCMPL){
                counter.fcmpl();
            }
            else if(opcode == Opcodes.FCMPG){
                counter.fcmpg();
            }
            else if(opcode == Opcodes.DCMPL){
                counter.dcmpl();
            }
            else if(opcode == Opcodes.DCMPG){
                counter.dcmpg();
            }
            else if(opcode == Opcodes.IRETURN){
                counter.ireturn();
            }
            else if(opcode == Opcodes.LRETURN){
                counter.lreturn();
            }
            else if(opcode == Opcodes.FRETURN){
                counter.freturn();
            }
            else if(opcode == Opcodes.DRETURN){
                counter.dreturn();
            }
            else if(opcode == Opcodes.ARETURN){
                counter.areturn();
            }
            else if(opcode == Opcodes.RETURN){
                counter.returnInsn();
            }
            else if(opcode == Opcodes.ARRAYLENGTH){
                counter.arraylength();
            }
            else if(opcode == Opcodes.ATHROW){
                counter.athrow();
            }
            else if(opcode == Opcodes.MONITORENTER){
                counter.monitorenter();
            }
            else if(opcode == Opcodes.MONITOREXIT){
                counter.monitorexit();
            }

            super.visitInsn(opcode);
        }

        @Override
        public void visitIntInsn(int opcode, int operand){
            if(opcode == Opcodes.BIPUSH){
                counter.bipush();
            }
            else if(opcode == Opcodes.SIPUSH){
                counter.sipush();
            }
            else if(opcode == Opcodes.NEWARRAY){
                counter.newarray();
            }
            super.visitIntInsn(opcode, operand);
        }

        @Override
        public void visitJumpInsn(int opcode, Label label){
            if(opcode == Opcodes.IFEQ){
                counter.ifeq();
            }
            else if(opcode == Opcodes.IFNE){
                counter.ifne();
            }
            else if(opcode == Opcodes.IFLT){
                counter.iflt();
            }
            else if(opcode == Opcodes.IFGE){
                counter.ifge();
            }
            else if(opcode == Opcodes.IFGT){
                counter.ifgt();
            }
            else if(opcode == Opcodes.IFLE){
                counter.ifle();
            }
            else if(opcode == Opcodes.IF_ICMPEQ){
                counter.ifIcmpeq();
            }
            else if(opcode == Opcodes.IF_ICMPNE){
                counter.ifIcmpne();
            }
            else if(opcode == Opcodes.IF_ICMPLT){
                counter.ifIcmplt();
            }
            else if(opcode == Opcodes.IF_ICMPGE){
                counter.ifIcmpge();
            }
            else if(opcode == Opcodes.IF_ICMPGT){
                counter.ifIcmpgt();
            }
            else if(opcode == Opcodes.IF_ICMPLE){
                counter.ifIcmple();
            }
            else if(opcode == Opcodes.IF_ACMPEQ){
                counter.ifAcmpeq();
            }
            else if(opcode == Opcodes.IF_ACMPNE){
                counter.ifAcmpne();
            }
            else if(opcode == Opcodes.GOTO){
                counter.gotoInsn();
            }
            else if(opcode == Opcodes.JSR){
                counter.jsr();
            }
            else if(opcode == Opcodes.IFNULL){
                counter.ifnull();
            }
            else if(opcode == Opcodes.IFNONNULL){
                counter.ifnonnull();
            }

            super.visitJumpInsn(opcode, label);
        }

        @Override
        public void visitLdcInsn(Object object){
            if(object instanceof Double || object instanceof Long){
                counter.ldc2();
            }
            else{
                counter.ldc();
            }
            super.visitLdcInsn(object);
        }

        @Override
        public void visitLineNumber(int line, Label label){
            counter.visitLine(line);
            super.visitLineNumber(line, label);
        }

        @Override
        public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index){
            super.visitLocalVariable(name, desc, signature, start, end, index);
        }

        @Override
        public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2){
            counter.lookupswitch();
            super.visitLookupSwitchInsn(arg0, arg1, arg2);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc){
            if(opcode == Opcodes.INVOKEINTERFACE){
                counter.invokeinterface();
            }
            else if(opcode == Opcodes.INVOKESPECIAL){
                counter.invokespecial();
            }
            else if(opcode == Opcodes.INVOKEVIRTUAL){
                counter.invokevirtual();
            }
            else if(opcode == Opcodes.INVOKESTATIC){
                counter.invokestatic();
            }
            super.visitMethodInsn(opcode, owner, name, desc);
        }

        @Override
        public void visitMultiANewArrayInsn(String desc, int dims){
            counter.multianewarray();
            super.visitMultiANewArrayInsn(desc, dims);
        }

        @Override
        public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels){
            counter.tableswitch();
            super.visitTableSwitchInsn(min, max, dflt, labels);
        }

        @Override
        public void visitTypeInsn(int opcode, String type){
            if(opcode == Opcodes.NEW){
                counter.newInsn();
            }
            else if(opcode == Opcodes.ANEWARRAY){
                counter.anewarray();
            }
            else if(opcode == Opcodes.CHECKCAST){
                counter.checkcast();
            }
            else if(opcode == Opcodes.INSTANCEOF){
                counter.instanceofInsn();
            }
            super.visitTypeInsn(opcode, type);
        }

        @Override
        public void visitVarInsn(int opcode, int var){
            if(opcode == Opcodes.ILOAD){
                counter.iload();
            }
            else if(opcode == Opcodes.LLOAD){
                counter.lload();
            }
            else if(opcode == Opcodes.FLOAD){
                counter.fload();
            }
            else if(opcode == Opcodes.DLOAD){
                counter.dload();
            }
            else if(opcode == Opcodes.ALOAD){
                counter.aload();
            }
            else if(opcode == Opcodes.ISTORE){
                counter.istore();
            }
            else if(opcode == Opcodes.LSTORE){
                counter.lstore();
            }
            else if(opcode == Opcodes.FSTORE){
                counter.fstore();
            }
            else if(opcode == Opcodes.DSTORE){
                counter.dstore();
            }
            else if(opcode == Opcodes.ASTORE){
                counter.astore();
            }
            else if(opcode == Opcodes.RET){
                counter.ret();
            }
                       
            super.visitVarInsn(opcode, var);
        }
    }
}
