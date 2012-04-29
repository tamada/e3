package jp.cafebabe.e3.exec;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * empty {@link MethodEntropyCounter <code>MethodEntropyCounter</code>}.
 * @see MethodEntropyCounter
 * @author Haruaki Tamada
 */
public class EmptyMethodEntropyCounter implements EntropyCounter{
    public Iterator<Integer> iterator(){
        return new ArrayList<Integer>().iterator();
    }

    public int getSize(){
        return 0;
    }

    public void summarize(){ }

    public void aaload(){ }

    public void aastore(){ }

    public void aconstNull(){ }

    public void aload(){ }

    public void anewarray(){ }

    public void areturn(){ }

    public void arraylength(){ }

    public void astore(){ }

    public void athrow(){ }

    public void baload(){ }

    public void bastore(){ }

    public void bipush(){ }

    public void breakpoint(){ }

    public void caload(){ }

    public void castore(){ }

    public void checkcast(){ }

    public void d2f(){ }

    public void d2i(){ }

    public void d2l(){ }

    public void dadd(){ }

    public void daload(){ }

    public void dastore(){ }

    public void dcmpg(){ }

    public void dcmpl(){ }

    public void dconst0(){ }

    public void dconst1(){ }

    public void ddiv(){ }

    public void dload(){ }

    public void dmul(){ }

    public void dneg(){ }

    public void drem(){ }

    public void dreturn(){ }

    public void dstore(){ }

    public void dsub(){ }

    public void dup(){ }

    public void dup2(){ }

    public void dup2X1(){ }

    public void dup2X2(){ }

    public void dupX1(){ }

    public void dupX2(){ }

    public void f2d(){ }

    public void f2i(){ }

    public void f2l(){ }

    public void fadd(){ }

    public void faload(){ }

    public void fastore(){ }

    public void fcmpg(){ }

    public void fcmpl(){ }

    public void fconst0(){ }

    public void fconst1(){ }

    public void fconst2(){ }

    public void fdiv(){ }

    public void fload(){ }

    public void fmul(){ }

    public void fneg(){ }

    public void frem(){ }

    public void freturn(){ }

    public void fstore(){ }

    public void fsub(){ }

    public void getfield(){ }

    public void getfield2(){ }

    public void getstatic(){ }

    public void getstatic2(){ }

    public void gotoInsn(){ }

    public void i2b(){ }

    public void i2c(){ }

    public void i2d(){ }

    public void i2f(){ }

    public void i2l(){ }

    public void i2s(){ }

    public void iadd(){ }

    public void iaload(){ }

    public void iand(){ }

    public void iastore(){ }

    public void iconstM1(){ }

    public void iconst0(){ }

    public void iconst1(){ }

    public void iconst2(){ }

    public void iconst3(){ }

    public void iconst4(){ }

    public void iconst5(){ }

    public void idiv(){ }

    public void ifAcmpeq(){ }

    public void ifAcmpne(){ }

    public void ifIcmpeq(){ }

    public void ifIcmpge(){ }

    public void ifIcmpgt(){ }

    public void ifIcmple(){ }

    public void ifIcmplt(){ }

    public void ifIcmpne(){ }

    public void ifeq(){ }

    public void ifge(){ }

    public void ifgt(){ }

    public void ifle(){ }

    public void iflt(){ }

    public void ifne(){ }

    public void ifnonnull(){ }

    public void ifnull(){ }

    public void iinc(){ }

    public void iload(){ }

    public void impdep(){ }

    public void imul(){ }

    public void ineg(){ }

    public void instanceofInsn(){ }

    public void invokeinterface(){ }

    public void invokenonvirtual(){ }

    public void invokespecial(){ }

    public void invokestatic(){ }

    public void invokesuper(){ }

    public void invokevirtual(){ }

    public void invokevirtualobject(){ }

    public void ior(){ }

    public void irem(){ }

    public void ireturn(){ }

    public void ishl(){ }

    public void ishr(){ }

    public void istore(){ }

    public void isub(){ }

    public void iushr(){ }

    public void ixor(){ }

    public void jsr(){ }

    public void l2d(){ }

    public void l2f(){ }

    public void l2i(){ }

    public void ladd(){ }

    public void laload(){ }

    public void land(){ }

    public void lastore(){ }

    public void lcmp(){ }

    public void lconst0(){ }

    public void lconst1(){ }

    public void ldc(){ }

    public void ldc2(){ }

    public void ldiv(){ }

    public void lload(){ }

    public void lmul(){ }

    public void lneg(){ }

    public void lookupswitch(){ }

    public void lor(){ }

    public void lrem(){ }

    public void lreturn(){ }

    public void lshl(){ }

    public void lshr(){ }

    public void lstore(){ }

    public void lsub(){ }

    public void lushr(){ }

    public void lxor(){ }

    public void monitorenter(){ }

    public void monitorexit(){ }

    public void multianewarray(){ }

    public void newInsn(){ }

    public void newarray(){ }

    public void noMethod(){ }

    public void nop(){ }

    public void pop(){ }

    public void pop2(){ }

    public void putfield(){ }

    public void putfield2(){ }

    public void putstatic(){ }

    public void putstatic2(){ }

    public void ret(){ }

    public void returnInsn(){ }

    public void saload(){ }

    public void sastore(){ }

    public void sipush(){ }

    public void swap(){ }

    public void tableswitch(){ }
}
