package jp.cafebabe.e3.exec;

import java.util.Iterator;

import jp.cafebabe.e3.exec.result.OpcodeFrequency;

/**
 * <p>
 * Counting executed opcodes interface at runtime for calculating
 * entropy of the program.
 * </p><p>
 * This interface has many methods. However, except 3 methods are for
 * counting instruction.  The 3 methods are as follows.
 * </p>
 * <ul>
 *   <li>{@link #iterator <code>iterator</code>}</li>
 *   <li>{@link #getSize <code>getSize</code>}</li>
 *   <li>{@link #summarize <code>summarize</code>}</li>
 * </ul>
 * @author Haruaki Tamada
 */
public interface EntropyCounter{
    /**
     * returns opcodes sequence in this object.
     * @return opcodes sequence.
     */
    Iterator<Integer> opcodes();

    /**
     * returns frequency of opcodes in this object ordered by opcode.
     * @return opcode frequency sequence.
     */
    Iterator<OpcodeFrequency> frequencies();

    /**
     * returns length of opcode sequence.  Return value of this method
     * is greater equals than 0.
     * @return length of opcode sequence.
     */
    int getSize();

    /**
     * Returns entropy.
     * @return
     */
    double getEntropy();

    /**
     * returns line number in given opcode length.
     * @param current opcode size
     * @return line number
     */
    int getLine(int opcodeSize);

    /**
     * update current line.
     */
    void visitLine(int line);

    /**
     * This method is called at executed AALOAD instruction.
     */
    void aaload();

    /**
     * This method is called at execution of AASTORE instruction.
     */
    void aastore();

    /**
     * This method is called at execution of ACONST_NULL instruction.
     */
    void aconstNull();

    /**
     * This method is called at execution of ALOAD instruction.
     */
    void aload();

    /**
     * This method is called at execution of ANEWARRAY instruction.
     */
    void anewarray();

    /**
     * This method is called at execution of ARETURN instruction.
     */
    void areturn();

    /**
     * This method is called at execution of ARRAYLENGTH instruction.
     */
    void arraylength();

    /**
     * This method is called at execution of ASTORE instruction.
     */
    void astore();

    /**
     * This method is called at execution of ATHROW instruction.
     */
    void athrow();

    /**
     * This method is called at execution of BALOAD instruction.
     */
    void baload();

    /**
     * This method is called at execution of BASTORE instruction.
     */
    void bastore();

    /**
     * This method is called at execution of BIPUSH instruction.
     */
    void bipush();

    /**
     * This method is called at execution of BREAKPOINT instruction.
     */
    void breakpoint();

    /**
     * This method is called at execution of CALOAD instruction.
     */
    void caload();

    /**
     * This method is called at execution of CASTORE instruction.
     */
    void castore();

    /**
     * This method is called at execution of CHECKCAST instruction.
     */
    void checkcast();

    /**
     * This method is called at execution oF D2F instruction.
     */
    void d2f();

    /**
     * This method is called at execution oF D2I instruction.
     */
    void d2i();

    /**
     * This method is called at execution oF D2L instruction.
     */
    void d2l();

    /**
     * This method is called at execution of DADD instruction.
     */
    void dadd();

    /**
     * This method is called at execution of DALOAD instruction.
     */
    void daload();

    /**
     * This method is called at execution of DASTORE instruction.
     */
    void dastore();

    /**
     * This method is called at execution of DCMPG instruction.
     */
    void dcmpg();

    /**
     * This method is called at execution of DCMPL instruction.
     */
    void dcmpl();

    /**
     * This method is called at execution of DCONST_0 instruction.
     */
    void dconst0();

    /**
     * This method is called at execution of DCONST_1 instruction.
     */
    void dconst1();

    /**
     * This method is called at execution of DDIV instruction.
     */
    void ddiv();

    /**
     * This method is called at execution of DLOAD instruction.
     */
    void dload();

    /**
     * This method is called at execution of DMUL instruction.
     */
    void dmul();

    /**
     * This method is called at execution of DNEG instruction.
     */
    void dneg();

    /**
     * This method is called at execution of DREM instruction.
     */
    void drem();

    /**
     * This method is called at execution of DRETURN instruction.
     */
    void dreturn();

    /**
     * This method is called at execution of DSTORE instruction.
     */
    void dstore();

    /**
     * This method is called at execution of DSUB instruction.
     */
    void dsub();

    /**
     * This method is called at execution oF DUP instruction.
     */
    void dup();

    /**
     * This method is called at execution of DUP2 instruction.
     */
    void dup2();

    /**
     * This method is called at execution of DUP2_X1 instruction.
     */
    void dup2X1();

    /**
     * This method is called at execution of DUP2_X2 instruction.
     */
    void dup2X2();

    /**
     * This method is called at execution of DUP_X1 instruction.
     */
    void dupX1();

    /**
     * This method is called at execution of DUP_X2 instruction.
     */
    void dupX2();

    /**
     * This method is called at execution oF F2D instruction.
     */
    void f2d();

    /**
     * This method is called at execution oF F2I instruction.
     */
    void f2i();

    /**
     * This method is called at execution oF F2L instruction.
     */
    void f2l();

    /**
     * This method is called at execution of FADD instruction.
     */
    void fadd();

    /**
     * This method is called at execution of FALOAD instruction.
     */
    void faload();

    /**
     * This method is called at execution of FASTORE instruction.
     */
    void fastore();

    /**
     * This method is called at execution of FCMPG instruction.
     */
    void fcmpg();

    /**
     * This method is called at execution of FCMPL instruction.
     */
    void fcmpl();

    /**
     * This method is called at execution of FCONST_0 instruction.
     */
    void fconst0();

    /**
     * This method is called at execution of FCONST_1 instruction.
     */
    void fconst1();

    /**
     * This method is called at execution of FCONST_2 instruction.
     */
    void fconst2();

    /**
     * This method is called at execution of FDIV instruction.
     */
    void fdiv();

    /**
     * This method is called at execution of FLOAD instruction.
     */
    void fload();

    /**
     * This method is called at execution of FMUL instruction.
     */
    void fmul();

    /**
     * This method is called at execution of FNEG instruction.
     */
    void fneg();

    /**
     * This method is called at execution of FREM instruction.
     */
    void frem();

    /**
     * This method is called at execution of FRETURN instruction.
     */
    void freturn();

    /**
     * This method is called at execution of FSTORE instruction.
     */
    void fstore();

    /**
     * This method is called at execution of FSUB instruction.
     */
    void fsub();

    /**
     * This method is called at execution of GETFIELD instruction.
     */
    void getfield();

    /**
     * This method is called at execution of GETFIELD2 instruction.
     */
    void getfield2();

    /**
     * This method is called at execution of GETSTATIC instruction.
     */
    void getstatic();

    /**
     * This method is called at execution of GETSTATIC2 instruction.
     */
    void getstatic2();

    /**
     * This method is called at execution of GOTO instruction.
     */
    void gotoInsn();

    /**
     * This method is called at execution oF I2B instruction.
     */
    void i2b();

    /**
     * This method is called at execution oF I2C instruction.
     */
    void i2c();

    /**
     * This method is called at execution oF I2D instruction.
     */
    void i2d();

    /**
     * This method is called at execution oF I2F instruction.
     */
    void i2f();

    /**
     * This method is called at execution oF I2L instruction.
     */
    void i2l();

    /**
     * This method is called at execution oF I2S instruction.
     */
    void i2s();

    /**
     * This method is called at execution of IADD instruction.
     */
    void iadd();

    /**
     * This method is called at execution of IALOAD instruction.
     */
    void iaload();

    /**
     * This method is called at execution of IAND instruction.
     */
    void iand();

    /**
     * This method is called at execution of IASTORE instruction.
     */
    void iastore();

    /**
     * This method is called at execution of ICONST_M1 instruction.
     */
    void iconstM1();

    /**
     * This method is called at execution of ICONST_0 instruction.
     */
    void iconst0();

    /**
     * This method is called at execution of ICONST_1 instruction.
     */
    void iconst1();

    /**
     * This method is called at execution of ICONST_2 instruction.
     */
    void iconst2();

    /**
     * This method is called at execution of ICONST_3 instruction.
     */
    void iconst3();

    /**
     * This method is called at execution of ICONST_4 instruction.
     */
    void iconst4();

    /**
     * This method is called at execution of ICONST_5 instruction.
     */
    void iconst5();

    /**
     * This method is called at execution of IDIV instruction.
     */
    void idiv();

    /**
     * This method is called at execution of IF_ACMPEQ instruction.
     */
    void ifAcmpeq();

    /**
     * This method is called at execution of IF_ACMPNE instruction.
     */
    void ifAcmpne();

    /**
     * This method is called at execution of IF_ICMPEQ instruction.
     */
    void ifIcmpeq();

    /**
     * This method is called at execution of IF_ICMPGE instruction.
     */
    void ifIcmpge();

    /**
     * This method is called at execution of IF_ICMPGT instruction.
     */
    void ifIcmpgt();

    /**
     * This method is called at execution of IF_ICMPLE instruction.
     */
    void ifIcmple();

    /**
     * This method is called at execution of IF_ICMPLT instruction.
     */
    void ifIcmplt();

    /**
     * This method is called at execution of IF_ICMPNE instruction.
     */
    void ifIcmpne();

    /**
     * This method is called at execution of IFEQ instruction.
     */
    void ifeq();

    /**
     * This method is called at execution of IFGE instruction.
     */
    void ifge();

    /**
     * This method is called at execution of IFGT instruction.
     */
    void ifgt();

    /**
     * This method is called at execution of IFLE instruction.
     */
    void ifle();

    /**
     * This method is called at execution of IFLT instruction.
     */
    void iflt();

    /**
     * This method is called at execution of IFNE instruction.
     */
    void ifne();

    /**
     * This method is called at execution of IFNONNULL instruction.
     */
    void ifnonnull();

    /**
     * This method is called at execution of IFNULL instruction.
     */
    void ifnull();

    /**
     * This method is called at execution of IINC instruction.
     */
    void iinc();

    /**
     * This method is called at execution of ILOAD instruction.
     */
    void iload();

    /**
     * This method is called at execution of IMPDEP instruction.
     */
    void impdep();

    /**
     * This method is called at execution of IMUL instruction.
     */
    void imul();

    /**
     * This method is called at execution of INEG instruction.
     */
    void ineg();

    /**
     * This method is called at execution of INSTANCEOF instruction.
     */
    void instanceofInsn();

    /**
     * This method is called at execution of INVOKEINTERFACE instruction.
     */
    void invokeinterface();

    /**
     * This method is called at execution of INVOKENONVIRTUAL instruction.
     */
    void invokenonvirtual();

    /**
     * This method is called at execution of INVOKESPECIAL instruction.
     */
    void invokespecial();

    /**
     * This method is called at execution of INVOKESTATIC instruction.
     */
    void invokestatic();

    /**
     * This method is called at execution of INVOKESUPER instruction.
     */
    void invokesuper();

    /**
     * This method is called at execution of INVOKEVIRTUAL instruction.
     */
    void invokevirtual();

    /**
     * This method is called at execution of INVOKEVIRTUALOBJECT instruction.
     */
    void invokevirtualobject();

    /**
     * This method is called at execution of IOR instruction.
     */
    void ior();

    /**
     * This method is called at execution of IREM instruction.
     */
    void irem();

    /**
     * This method is called at execution of IRETURN instruction.
     */
    void ireturn();

    /**
     * This method is called at execution of ISHL instruction.
     */
    void ishl();

    /**
     * This method is called at execution of ISHR instruction.
     */
    void ishr();

    /**
     * This method is called at execution of ISTORE instruction.
     */
    void istore();

    /**
     * This method is called at execution of ISUB instruction.
     */
    void isub();

    /**
     * This method is called at execution of IUSHR instruction.
     */
    void iushr();

    /**
     * This method is called at execution of IXOR instruction.
     */
    void ixor();

    /**
     * This method is called at execution oF JSR instruction.
     */
    void jsr();

    /**
     * This method is called at execution oF L2D instruction.
     */
    void l2d();

    /**
     * This method is called at execution oF L2F instruction.
     */
    void l2f();

    /**
     * This method is called at execution oF L2I instruction.
     */
    void l2i();

    /**
     * This method is called at execution of LADD instruction.
     */
    void ladd();

    /**
     * This method is called at execution of LALOAD instruction.
     */
    void laload();

    /**
     * This method is called at execution of LAND instruction.
     */
    void land();

    /**
     * This method is called at execution of LASTORE instruction.
     */
    void lastore();

    /**
     * This method is called at execution of LCMP instruction.
     */
    void lcmp();

    /**
     * This method is called at execution of LCONST_0 instruction.
     */
    void lconst0();

    /**
     * This method is called at execution of LCONST_1 instruction.
     */
    void lconst1();

    /**
     * This method is called at execution oF LDC instruction.
     */
    void ldc();

    /**
     * This method is called at execution of LDC2 instruction.
     */
    void ldc2();

    /**
     * This method is called at execution of LDIV instruction.
     */
    void ldiv();

    /**
     * This method is called at execution of LLOAD instruction.
     */
    void lload();

    /**
     * This method is called at execution of LMUL instruction.
     */
    void lmul();

    /**
     * This method is called at execution of LNEG instruction.
     */
    void lneg();

    /**
     * This method is called at execution of LOOKUPSWITCH instruction.
     */
    void lookupswitch();

    /**
     * This method is called at execution oF LOR instruction.
     */
    void lor();

    /**
     * This method is called at execution of LREM instruction.
     */
    void lrem();

    /**
     * This method is called at execution of LRETURN instruction.
     */
    void lreturn();

    /**
     * This method is called at execution of LSHL instruction.
     */
    void lshl();

    /**
     * This method is called at execution of LSHR instruction.
     */
    void lshr();

    /**
     * This method is called at execution of LSTORE instruction.
     */
    void lstore();

    /**
     * This method is called at execution of LSUB instruction.
     */
    void lsub();

    /**
     * This method is called at execution of LUSHR instruction.
     */
    void lushr();

    /**
     * This method is called at execution of LXOR instruction.
     */
    void lxor();

    /**
     * This method is called at execution of MONITORENTER instruction.
     */
    void monitorenter();

    /**
     * This method is called at execution of MONITOREXIT instruction.
     */
    void monitorexit();

    /**
     * This method is called at execution of MULTIANEWARRAY instruction.
     */
    void multianewarray();

    /**
     * This method is called at execution of NEW instruction.
     */
    void newInsn();

    /**
     * This method is called at execution of NEWARRAY instruction.
     */
    void newarray();

    /**
     * This method is never called because corresponding opcodes are
     * UNUSED and WIDE.  Those opcodes do not affect runtime opcode
     * sequence.  UNUSED opcode is never used, and WIDE opcode only
     * expands operand index.
     */
    void noMethod();

    /**
     * This method is called at execution oF NOP instruction.
     */
    void nop();

    /**
     * This method is called at execution oF POP instruction.
     */
    void pop();

    /**
     * This method is called at execution of POP2 instruction.
     */
    void pop2();

    /**
     * This method is called at execution of PUTFIELD instruction.
     */
    void putfield();

    /**
     * This method is called at execution of PUTFIELD2 instruction.
     */
    void putfield2();

    /**
     * This method is called at execution of PUTSTATIC instruction.
     */
    void putstatic();

    /**
     * This method is called at execution of PUTSTATIC2 instruction.
     */
    void putstatic2();

    /**
     * This method is called at execution oF RET instruction.
     */
    void ret();

    /**
     * This method is called at execution of RETURN instruction.
     */
    void returnInsn();

    /**
     * This method is called at execution of SALOAD instruction.
     */
    void saload();

    /**
     * This method is called at execution of SASTORE instruction.
     */
    void sastore();

    /**
     * This method is called at execution of SIPUSH instruction.
     */
    void sipush();

    /**
     * This method is called at execution of SWAP instruction.
     */
    void swap();

    /**
     * This method is called at execution of TABLESWITCH instruction.
     */
    void tableswitch();
}
