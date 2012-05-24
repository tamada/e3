package jp.cafebabe.e3.exec;

import java.util.ArrayList;
import java.util.Iterator;

import jp.cafebabe.e3.exec.result.OpcodeFrequency;

/**
 * empty {@link MethodEntropyCounter <code>MethodEntropyCounter</code>}.
 * @see MethodEntropyCounter
 * @author Haruaki Tamada
 */
public class EmptyEntropyCounter implements EntropyCounter{
    private static final long serialVersionUID = -7880172480418927135L;

    public Iterator<Integer> opcodes(){
        return new ArrayList<Integer>().iterator();
    }

    public Iterator<OpcodeFrequency> frequencies(){
        return new ArrayList<OpcodeFrequency>().iterator();
    }

    public double getEntropy(){
        return 0d;
    }

    @Override
    public int getSize(){
        return 0;
    }

    @Override
    public int getLine(int opcodeSize){
        return -1;
    }

    @Override
    public void visitLine(int line){ }

    @Override
    public void aaload(){ }

    @Override
    public void aastore(){ }

    @Override
    public void aconstNull(){ }

    @Override
    public void aload(){ }

    @Override
    public void anewarray(){ }

    @Override
    public void areturn(){ }

    @Override
    public void arraylength(){ }

    @Override
    public void astore(){ }

    @Override
    public void athrow(){ }

    @Override
    public void baload(){ }

    @Override
    public void bastore(){ }

    @Override
    public void bipush(){ }

    @Override
    public void breakpoint(){ }

    @Override
    public void caload(){ }

    @Override
    public void castore(){ }

    @Override
    public void checkcast(){ }

    @Override
    public void d2f(){ }

    @Override
    public void d2i(){ }

    @Override
    public void d2l(){ }

    @Override
    public void dadd(){ }

    @Override
    public void daload(){ }

    @Override
    public void dastore(){ }

    @Override
    public void dcmpg(){ }

    @Override
    public void dcmpl(){ }

    @Override
    public void dconst0(){ }

    @Override
    public void dconst1(){ }

    @Override
    public void ddiv(){ }

    @Override
    public void dload(){ }

    @Override
    public void dmul(){ }

    @Override
    public void dneg(){ }

    @Override
    public void drem(){ }

    @Override
    public void dreturn(){ }

    @Override
    public void dstore(){ }

    @Override
    public void dsub(){ }

    @Override
    public void dup(){ }

    @Override
    public void dup2(){ }

    @Override
    public void dup2X1(){ }

    @Override
    public void dup2X2(){ }

    @Override
    public void dupX1(){ }

    @Override
    public void dupX2(){ }

    @Override
    public void f2d(){ }

    @Override
    public void f2i(){ }

    @Override
    public void f2l(){ }

    @Override
    public void fadd(){ }

    @Override
    public void faload(){ }

    @Override
    public void fastore(){ }

    @Override
    public void fcmpg(){ }

    @Override
    public void fcmpl(){ }

    @Override
    public void fconst0(){ }

    @Override
    public void fconst1(){ }

    @Override
    public void fconst2(){ }

    @Override
    public void fdiv(){ }

    @Override
    public void fload(){ }

    @Override
    public void fmul(){ }

    @Override
    public void fneg(){ }

    @Override
    public void frem(){ }

    @Override
    public void freturn(){ }

    @Override
    public void fstore(){ }

    @Override
    public void fsub(){ }

    @Override
    public void getfield(){ }

    @Override
    public void getfield2(){ }

    @Override
    public void getstatic(){ }

    @Override
    public void getstatic2(){ }

    @Override
    public void gotoInsn(){ }

    @Override
    public void i2b(){ }

    @Override
    public void i2c(){ }

    @Override
    public void i2d(){ }

    @Override
    public void i2f(){ }

    @Override
    public void i2l(){ }

    @Override
    public void i2s(){ }

    @Override
    public void iadd(){ }

    @Override
    public void iaload(){ }

    @Override
    public void iand(){ }

    @Override
    public void iastore(){ }

    @Override
    public void iconstM1(){ }

    @Override
    public void iconst0(){ }

    @Override
    public void iconst1(){ }

    @Override
    public void iconst2(){ }

    @Override
    public void iconst3(){ }

    @Override
    public void iconst4(){ }

    @Override
    public void iconst5(){ }

    @Override
    public void idiv(){ }

    @Override
    public void ifAcmpeq(){ }

    @Override
    public void ifAcmpne(){ }

    @Override
    public void ifIcmpeq(){ }

    @Override
    public void ifIcmpge(){ }

    @Override
    public void ifIcmpgt(){ }

    @Override
    public void ifIcmple(){ }

    @Override
    public void ifIcmplt(){ }

    @Override
    public void ifIcmpne(){ }

    @Override
    public void ifeq(){ }

    @Override
    public void ifge(){ }

    @Override
    public void ifgt(){ }

    @Override
    public void ifle(){ }

    @Override
    public void iflt(){ }

    @Override
    public void ifne(){ }

    @Override
    public void ifnonnull(){ }

    @Override
    public void ifnull(){ }

    @Override
    public void iinc(){ }

    @Override
    public void iload(){ }

    @Override
    public void impdep(){ }

    @Override
    public void imul(){ }

    @Override
    public void ineg(){ }

    @Override
    public void instanceofInsn(){ }

    @Override
    public void invokeinterface(){ }

    @Override
    public void invokenonvirtual(){ }

    @Override
    public void invokespecial(){ }

    @Override
    public void invokestatic(){ }

    @Override
    public void invokesuper(){ }

    @Override
    public void invokevirtual(){ }

    @Override
    public void invokevirtualobject(){ }

    @Override
    public void ior(){ }

    @Override
    public void irem(){ }

    @Override
    public void ireturn(){ }

    @Override
    public void ishl(){ }

    @Override
    public void ishr(){ }

    @Override
    public void istore(){ }

    @Override
    public void isub(){ }

    @Override
    public void iushr(){ }

    @Override
    public void ixor(){ }

    @Override
    public void jsr(){ }

    @Override
    public void l2d(){ }

    @Override
    public void l2f(){ }

    @Override
    public void l2i(){ }

    @Override
    public void ladd(){ }

    @Override
    public void laload(){ }

    @Override
    public void land(){ }

    @Override
    public void lastore(){ }

    @Override
    public void lcmp(){ }

    @Override
    public void lconst0(){ }

    @Override
    public void lconst1(){ }

    @Override
    public void ldc(){ }

    @Override
    public void ldc2(){ }

    @Override
    public void ldiv(){ }

    @Override
    public void lload(){ }

    @Override
    public void lmul(){ }

    @Override
    public void lneg(){ }

    @Override
    public void lookupswitch(){ }

    @Override
    public void lor(){ }

    @Override
    public void lrem(){ }

    @Override
    public void lreturn(){ }

    @Override
    public void lshl(){ }

    @Override
    public void lshr(){ }

    @Override
    public void lstore(){ }

    @Override
    public void lsub(){ }

    @Override
    public void lushr(){ }

    @Override
    public void lxor(){ }

    @Override
    public void monitorenter(){ }

    @Override
    public void monitorexit(){ }

    @Override
    public void multianewarray(){ }

    @Override
    public void newInsn(){ }

    @Override
    public void newarray(){ }

    @Override
    public void noMethod(){ }

    @Override
    public void nop(){ }

    @Override
    public void pop(){ }

    @Override
    public void pop2(){ }

    @Override
    public void putfield(){ }

    @Override
    public void putfield2(){ }

    @Override
    public void putstatic(){ }

    @Override
    public void putstatic2(){ }

    @Override
    public void ret(){ }

    @Override
    public void returnInsn(){ }

    @Override
    public void saload(){ }

    @Override
    public void sastore(){ }

    @Override
    public void sipush(){ }

    @Override
    public void swap(){ }

    @Override
    public void tableswitch(){ }
}
