package jp.cafebabe.e3.exec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Haruaki Tamada
 */
public class AbstractEntropyCounter implements EntropyCounter{
    private List<Integer> opcodes = new ArrayList<Integer>();

    /**
     * returns opcodes sequence in this object.
     * @return iterator object of opcode sequence.
     */
    public final Iterator<Integer> iterator(){
        return opcodes.iterator();
    }

    public final int getSize(){
        return opcodes.size();
    }

    @Override
    public void summarize(){
    }

    @Override
    public final void aaload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aaload"));
    }

    @Override
    public final void aastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aastore"));
    }

    @Override
    public final void aconstNull(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aconst_null"));
    }

    @Override
    public final void aload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aload"));
    }

    @Override
    public final void anewarray(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("anewarray"));
    }

    @Override
    public final void areturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("areturn"));
    }

    @Override
    public final void arraylength(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("arraylength"));
    }

    @Override
    public final void astore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("astore"));
    }

    @Override
    public final void athrow(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("athrow"));
    }

    @Override
    public final void baload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("baload"));
    }

    @Override
    public final void bastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("bastore"));
    }

    @Override
    public final void bipush(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("bipush"));
    }

    @Override
    public final void breakpoint(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("breakpoint"));
    }

    @Override
    public final void caload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("caload"));
    }

    @Override
    public final void castore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("castore"));
    }

    @Override
    public final void checkcast(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("checkcast"));
    }

    @Override
    public final void d2f(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("d2f"));
    }

    @Override
    public final void d2i(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("d2i"));
    }

    @Override
    public final void d2l(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("d2l"));
    }

    @Override
    public final void dadd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dadd"));
    }

    @Override
    public final void daload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("daload"));
    }

    @Override
    public final void dastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dastore"));
    }

    @Override
    public final void dcmpg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dcmpg"));
    }

    @Override
    public final void dcmpl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dcmpl"));
    }

    @Override
    public final void dconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dconst_0"));
    }

    @Override
    public final void dconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dconst_1"));
    }

    @Override
    public final void ddiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ddiv"));
    }

    @Override
    public final void dload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dload"));
    }

    @Override
    public final void dmul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dmul"));
    }

    @Override
    public final void dneg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dneg"));
    }

    @Override
    public final void drem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("drem"));
    }

    @Override
    public final void dreturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dreturn"));
    }

    @Override
    public final void dstore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dstore"));
    }

    @Override
    public final void dsub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dsub"));
    }

    @Override
    public final void dup(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup"));
    }

    @Override
    public final void dup2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup2"));
    }

    @Override
    public final void dup2X1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup2_x1"));
    }

    @Override
    public final void dup2X2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup2_x2"));
    }

    @Override
    public final void dupX1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup_x1"));
    }

    @Override
    public final void dupX2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup_x2"));
    }

    @Override
    public final void f2d(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("f2d"));
    }

    @Override
    public final void f2i(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("f2i"));
    }

    @Override
    public final void f2l(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("f2l"));
    }

    @Override
    public final void fadd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fadd"));
    }

    @Override
    public final void faload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("faload"));
    }

    @Override
    public final void fastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fastore"));
    }

    @Override
    public final void fcmpg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fcmpg"));
    }

    @Override
    public final void fcmpl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fcmpl"));
    }

    @Override
    public final void fconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fconst_0"));
    }

    @Override
    public final void fconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fconst_1"));
    }

    @Override
    public final void fconst2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fconst_2"));
    }

    @Override
    public final void fdiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fdiv"));
    }

    @Override
    public final void fload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fload"));
    }

    @Override
    public final void fmul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fmul"));
    }

    @Override
    public final void fneg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fneg"));
    }

    @Override
    public final void frem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("frem"));
    }

    @Override
    public final void freturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("freturn"));
    }

    @Override
    public final void fstore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fstore"));
    }

    @Override
    public final void fsub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fsub"));
    }

    @Override
    public final void getfield(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getfield"));
    }

    @Override
    public final void getfield2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getfield2"));
    }

    @Override
    public final void getstatic(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getstatic"));
    }

    @Override
    public final void getstatic2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getstatic2"));
    }

    @Override
    public final void gotoInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("goto"));
    }

    @Override
    public final void i2b(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2b"));
    }

    @Override
    public final void i2c(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2c"));
    }

    @Override
    public final void i2d(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2d"));
    }

    @Override
    public final void i2f(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2f"));
    }

    @Override
    public final void i2l(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2l"));
    }

    @Override
    public final void i2s(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2s"));
    }

    @Override
    public final void iadd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iadd"));
    }

    @Override
    public final void iaload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iaload"));
    }

    @Override
    public final void iand(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iand"));
    }

    @Override
    public final void iastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iastore"));
    }

    @Override
    public final void iconstM1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_m1"));
    }

    @Override
    public final void iconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_0"));
    }

    @Override
    public final void iconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_1"));
    }

    @Override
    public final void iconst2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_2"));
    }

    @Override
    public final void iconst3(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_3"));
    }

    @Override
    public final void iconst4(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_4"));
    }

    @Override
    public final void iconst5(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_5"));
    }

    @Override
    public final void idiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("idiv"));
    }

    @Override
    public final void ifAcmpeq(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_acmpeq"));
    }

    @Override
    public final void ifAcmpne(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_acmpne"));
    }

    @Override
    public final void ifIcmpeq(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpeq"));
    }

    @Override
    public final void ifIcmpge(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpge"));
    }

    @Override
    public final void ifIcmpgt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpgt"));
    }

    @Override
    public final void ifIcmple(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmple"));
    }

    @Override
    public final void ifIcmplt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmplt"));
    }

    @Override
    public final void ifIcmpne(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpne"));
    }

    @Override
    public final void ifeq(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifeq"));
    }

    @Override
    public final void ifge(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifge"));
    }

    @Override
    public final void ifgt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifgt"));
    }

    @Override
    public final void ifle(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifle"));
    }

    @Override
    public final void iflt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iflt"));
    }

    @Override
    public final void ifne(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifne"));
    }

    @Override
    public final void ifnonnull(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifnonnull"));
    }

    @Override
    public final void ifnull(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifnull"));
    }

    @Override
    public final void iinc(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iinc"));
    }

    @Override
    public final void iload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iload"));
    }

    @Override
    public final void impdep(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("impdep"));
    }

    @Override
    public final void imul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("imul"));
    }

    @Override
    public final void ineg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ineg"));
    }

    @Override
    public final void instanceofInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("instanceof"));
    }

    @Override
    public final void invokeinterface(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokeinterface"));
    }

    @Override
    public final void invokenonvirtual(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokenonvirtual"));
    }

    @Override
    public final void invokespecial(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokespecial"));
    }

    @Override
    public final void invokestatic(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokestatic"));
    }

    @Override
    public final void invokesuper(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokesuper"));
    }

    @Override
    public final void invokevirtual(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokevirtual"));
    }

    @Override
    public final void invokevirtualobject(){
        opcodes.add(
            OpcodeManager.getInstance().getOpcode("invokevirtualobject")
        );
    }

    @Override
    public final void ior(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ior"));
    }

    @Override
    public final void irem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("irem"));
    }

    @Override
    public final void ireturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ireturn"));
    }

    @Override
    public final void ishl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ishl"));
    }

    @Override
    public final void ishr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ishr"));
    }

    @Override
    public final void istore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("istore"));
    }

    @Override
    public final void isub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("isub"));
    }

    @Override
    public final void iushr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iushr"));
    }

    @Override
    public final void ixor(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ixor"));
    }

    @Override
    public final void jsr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("jsr"));
    }

    @Override
    public final void l2d(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("l2d"));
    }

    @Override
    public final void l2f(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("l2f"));
    }

    @Override
    public final void l2i(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("l2i"));
    }

    @Override
    public final void ladd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ladd"));
    }

    @Override
    public final void laload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("laload"));
    }

    @Override
    public final void land(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("land"));
    }

    @Override
    public final void lastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lastore"));
    }

    @Override
    public final void lcmp(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lcmp"));
    }

    @Override
    public final void lconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lconst_0"));
    }

    @Override
    public final void lconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lconst_1"));
    }

    @Override
    public final void ldc(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ldc"));
    }

    @Override
    public final void ldc2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ldc2"));
    }

    @Override
    public final void ldiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ldiv"));
    }

    @Override
    public final void lload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lload"));
    }

    @Override
    public final void lmul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lmul"));
    }

    @Override
    public final void lneg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lneg"));
    }

    @Override
    public final void lookupswitch(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lookupswitch"));
    }

    @Override
    public final void lor(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lor"));
    }

    @Override
    public final void lrem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lrem"));
    }

    @Override
    public final void lreturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lreturn"));
    }

    @Override
    public final void lshl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lshl"));
    }

    @Override
    public final void lshr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lshr"));
    }

    @Override
    public final void lstore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lstore"));
    }

    @Override
    public final void lsub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lsub"));
    }

    @Override
    public final void lushr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lushr"));
    }

    @Override
    public final void lxor(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lxor"));
    }

    @Override
    public final void monitorenter(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("monitorenter"));
    }

    @Override
    public final void monitorexit(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("monitorexit"));
    }

    @Override
    public final void multianewarray(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("multianewarray"));
    }

    @Override
    public final void newInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("new"));
    }

    @Override
    public final void newarray(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("newarray"));
    }

    @Override
    public final void noMethod(){
    }

    @Override
    public final void nop(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("nop"));
    }

    @Override
    public final void pop(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("pop"));
    }

    @Override
    public final void pop2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("pop2"));
    }

    @Override
    public final void putfield(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putfield"));
    }

    @Override
    public final void putfield2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putfield2"));
    }

    @Override
    public final void putstatic(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putstatic"));
    }

    @Override
    public final void putstatic2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putstatic2"));
    }

    @Override
    public final void ret(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ret"));
    }

    @Override
    public final void returnInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("return"));
    }

    @Override
    public final void saload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("saload"));
    }

    @Override
    public final void sastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("sastore"));
    }

    @Override
    public final void sipush(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("sipush"));
    }

    @Override
    public final void swap(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("swap"));
    }

    @Override
    public final void tableswitch(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("tableswitch"));
    }
}
