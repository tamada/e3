package jp.cafebabe.e3.exec;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.cafebabe.e3.exec.result.DefaultResultSet;
import jp.cafebabe.e3.exec.result.ResultSet;

/**
 * <p>
 * Management class for {@link EntropyCounter <code>EntropyCounter</code>}.
 * The key methods of this class is as follows.  Show each method for more details.
 * </p>
 * <ul>
 *   <li>{@link #getResultSet <code>getResultSet</code>}</li>
 *   <li>{@link #clear        <code>clear</code>}</li>
 *   <li>{@link #summarize    <code>summarize</code>}</li>
 * </ul>
 * <p>
 * Other methods are for collecting executed instructions.
 * </p>
 * 
 * @author Haruaki Tamada
 */
public final class EntropyCounterManager{
    private static final EntropyCounterManager MANAGER = new EntropyCounterManager();
    private Map<String, List<EntropyCounter>> stackMap = new HashMap<String, List<EntropyCounter>>();
    private List<EntropyCounter> executionList = new ArrayList<EntropyCounter>();

    private EntropyCounterManager(){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                summarize();
            }
        });
    }

    /**
     * Returns singleton object of this class.
     * @return singleton object of this class.
     */
    public static EntropyCounterManager getInstance(){
        return MANAGER;
    }

    public static void entryMethod(final String className,
                            final String methodName){
        MANAGER.enterMethod(className, methodName);
    }

    public static void returnMethod(){
        MANAGER.exitMethod();
    }

    /**
     * This method is called for collecting runtime opcode sequence in
     * each method when the given method is invoked.
     * @param className class name
     * @param methodName is the method name which is invoked now. 
     */
    private synchronized void enterMethod(final String className, final String methodName){
        String threadName = Thread.currentThread().getName();
        List<EntropyCounter> stack = stackMap.get(threadName);
        if(stack == null){
            stack = new ArrayList<EntropyCounter>();
            stackMap.put(threadName, stack);
        }
        MethodEntropyCounter method = new MethodEntropyCounter(className, methodName, threadName);
        stack.add(method);
        executionList.add(method);
    }

    /**
     * This method is called for reporting current method is finished.
     * This method is called when IRETURN, LRETURN, FRETURN, DRETURN,
     * ARETURN, RETURN instruction were invoked.
     */
    private synchronized void exitMethod(){
        String threadName = Thread.currentThread().getName();
        List<EntropyCounter> stack = stackMap.get(threadName);
        if(stack == null || stack.size() == 0){
            throw new IllegalStateException();
        }
        stack.remove(stack.size() - 1);
    }

    /**
     * Returns results.
     * @return results.
     * @see ResultSet
     */
    public synchronized ResultSet getResultSet(){
        List<EntropyCounter> list = new ArrayList<EntropyCounter>(executionList);
        return new DefaultResultSet(list);
    }

    /**
     * 
     */
    public synchronized void summarize(){
        ResultSet rs = getResultSet();
        PrintWriter out = null;
        try{
            out = new PrintWriter(new OutputStreamWriter(System.out, "utf-8"));
            rs.print(out);
        } catch(UnsupportedEncodingException e){
        } finally{
            if(out != null){
                out.close();
            }
        }
    }

    /**
     * Clear executed instruction history.  Obtain results by
     * calling {@link #getResultSet <code>getResultSet</code>}
     * method before this method will be executed.
     */
    public synchronized void clear(){
        executionList.clear();
    }

    private static EntropyCounter getCounter(){
        String threadName = Thread.currentThread().getName();
        List<EntropyCounter> stack = MANAGER.stackMap.get(threadName);
        return stack.get(stack.size() - 1);
    }

    public static void visitLine(int line){
        getCounter().visitLine(line);
    }

    /**
     * This method is called when AALOAD was executed.
     */
    public static void aaload(){
        getCounter().aaload();
    }

    /**
     * This method is called when AASTORE was executed.
     */
    public static void aastore(){
        getCounter().aastore();
    }

    /**
     * This method is called when ACONST_NULL was executed.
     */
    public static void aconstNull(){
        getCounter().aconstNull();
    }

    /**
     * This method is called when ALOAD was executed.
     */
    public static void aload(){
        getCounter().aload();
    }

    /**
     * This method is called when ANEWARRAY was executed.
     */
    public static void anewarray(){
        getCounter().anewarray();
    }

    /**
     * This method is called when ARETURN was executed.
     */
    public static void areturn(){
        getCounter().areturn();
        returnMethod();
    }

    /**
     * This method is called when ARRAYLENGTH was executed.
     */
    public static void arraylength(){
        getCounter().arraylength();
    }

    /**
     * This method is called when ASTORE was executed.
     */
    public static void astore(){
        getCounter().astore();
    }

    /**
     * This method is called when ATHROW was executed.
     */
    public static void athrow(){
        getCounter().athrow();
    }

    /**
     * This method is called when BALOAD was executed.
     */
    public static void baload(){
        getCounter().baload();
    }

    /**
     * This method is called when BASTORE was executed.
     */
    public static void bastore(){
        getCounter().bastore();
    }

    /**
     * This method is called when BIPUSH was executed.
     */
    public static void bipush(){
        getCounter().bipush();
    }

    /**
     * This method is called when BREAKPOINT was executed.
     */
    public static void breakpoint(){
        getCounter().breakpoint();
    }

    /**
     * This method is called when CALOAD was executed.
     */
    public static void caload(){
        getCounter().caload();
    }

    /**
     * This method is called when CASTORE was executed.
     */
    public static void castore(){
        getCounter().castore();
    }

    /**
     * This method is called when CHECKCAST was executed.
     */
    public static void checkcast(){
        getCounter().checkcast();
    }

    /**
     * This method is called when D2F was executed.
     */
    public static void d2f(){
        getCounter().d2f();
    }

    /**
     * This method is called when D2I was executed.
     */
    public static void d2i(){
        getCounter().d2i();
    }

    /**
     * This method is called when D2L was executed.
     */
    public static void d2l(){
        getCounter().d2l();
    }

    /**
     * This method is called when DADD was executed.
     */
    public static void dadd(){
        getCounter().dadd();
    }

    /**
     * This method is called when DALOAD was executed.
     */
    public static void daload(){
        getCounter().daload();
    }

    /**
     * This method is called when DASTORE was executed.
     */
    public static void dastore(){
        getCounter().dastore();
    }

    /**
     * This method is called when DCMPG was executed.
     */
    public static void dcmpg(){
        getCounter().dcmpg();
    }

    /**
     * This method is called when DCMPL was executed.
     */
    public static void dcmpl(){
        getCounter().dcmpl();
    }

    /**
     * This method is called when DCONST_0 was executed.
     */
    public static void dconst0(){
        getCounter().dconst0();
    }

    /**
     * This method is called when DCONST_1 was executed.
     */
    public static void dconst1(){
        getCounter().dconst1();
    }

    /**
     * This method is called when DDIV was executed.
     */
    public static void ddiv(){
        getCounter().ddiv();
    }

    /**
     * This method is called when DLOAD was executed.
     */
    public static void dload(){
        getCounter().dload();
    }

    /**
     * This method is called when DMUL was executed.
     */
    public static void dmul(){
        getCounter().dmul();
    }

    /**
     * This method is called when DNEG was executed.
     */
    public static void dneg(){
        getCounter().dneg();
    }

    /**
     * This method is called when DREM was executed.
     */
    public static void drem(){
        getCounter().drem();
    }

    /**
     * This method is called when DRETURN was executed.
     */
    public static void dreturn(){
        getCounter().dreturn();
        returnMethod();
    }

    /**
     * This method is called when DSTORE was executed.
     */
    public static void dstore(){
        getCounter().dstore();
    }

    /**
     * This method is called when DSUB was executed.
     */
    public static void dsub(){
        getCounter().dsub();
    }

    /**
     * This method is called when DUP was executed.
     */
    public static void dup(){
        getCounter().dup();
    }

    /**
     * This method is called when DUP2 was executed.
     */
    public static void dup2(){
        getCounter().dup2();
    }

    /**
     * This method is called when DUP2_X1 was executed.
     */
    public static void dup2X1(){
        getCounter().dup2X1();
    }

    /**
     * This method is called when DUP2_X2 was executed.
     */
    public static void dup2X2(){
        getCounter().dup2X2();
    }

    /**
     * This method is called when DUP_X1 was executed.
     */
    public static void dupX1(){
        getCounter().dupX1();
    }

    /**
     * This method is called when DUP_X2 was executed.
     */
    public static void dupX2(){
        getCounter().dupX2();
    }

    /**
     * This method is called when F2D was executed.
     */
    public static void f2d(){
        getCounter().f2d();
    }

    /**
     * This method is called when F2I was executed.
     */
    public static void f2i(){
        getCounter().f2i();
    }

    /**
     * This method is called when F2L was executed.
     */
    public static void f2l(){
        getCounter().f2l();
    }

    /**
     * This method is called when FADD was executed.
     */
    public static void fadd(){
        getCounter().fadd();
    }

    /**
     * This method is called when FALOAD was executed.
     */
    public static void faload(){
        getCounter().faload();
    }

    /**
     * This method is called when FASTORE was executed.
     */
    public static void fastore(){
        getCounter().fastore();
    }

    /**
     * This method is called when FCMPG was executed.
     */
    public static void fcmpg(){
        getCounter().fcmpg();
    }

    /**
     * This method is called when FCMPL was executed.
     */
    public static void fcmpl(){
        getCounter().fcmpl();
    }

    /**
     * This method is called when FCONST_0 was executed.
     */
    public static void fconst0(){
        getCounter().fconst0();
    }

    /**
     * This method is called when FCONST_1 was executed.
     */
    public static void fconst1(){
        getCounter().fconst1();
    }

    /**
     * This method is called when FCONST_2 was executed.
     */
    public static void fconst2(){
        getCounter().fconst2();
    }

    /**
     * This method is called when FDIV was executed.
     */
    public static void fdiv(){
        getCounter().fdiv();
    }

    /**
     * This method is called when FLOAD was executed.
     */
    public static void fload(){
        getCounter().fload();
    }

    /**
     * This method is called when FMUL was executed.
     */
    public static void fmul(){
        getCounter().fmul();
    }

    /**
     * This method is called when FNEG was executed.
     */
    public static void fneg(){
        getCounter().fneg();
    }

    /**
     * This method is called when FREM was executed.
     */
    public static void frem(){
        getCounter().frem();
    }

    /**
     * This method is called when FRETURN was executed.
     */
    public static void freturn(){
        getCounter().freturn();
        returnMethod();
    }

    /**
     * This method is called when FSTORE was executed.
     */
    public static void fstore(){
        getCounter().fstore();
    }

    /**
     * This method is called when FSUB was executed.
     */
    public static void fsub(){
        getCounter().fsub();
    }

    /**
     * This method is called when GETFIELD was executed.
     */
    public static void getfield(){
        getCounter().getfield();
    }

    /**
     * This method is called when GETFIELD2 was executed.
     */
    public static void getfield2(){
        getCounter().getfield2();
    }

    /**
     * This method is called when GETSTATIC was executed.
     */
    public static void getstatic(){
        getCounter().getstatic();
    }

    /**
     * This method is called when GETSTATIC2 was executed.
     */
    public static void getstatic2(){
        getCounter().getstatic2();
    }

    /**
     * This method is called when GOTO was executed.
     */
    public static void gotoInsn(){
        getCounter().gotoInsn();
    }

    /**
     * This method is called when I2B was executed.
     */
    public static void i2b(){
        getCounter().i2b();
    }

    /**
     * This method is called when I2C was executed.
     */
    public static void i2c(){
        getCounter().i2c();
    }

    /**
     * This method is called when I2D was executed.
     */
    public static void i2d(){
        getCounter().i2d();
    }

    /**
     * This method is called when I2F was executed.
     */
    public static void i2f(){
        getCounter().i2f();
    }

    /**
     * This method is called when I2L was executed.
     */
    public static void i2l(){
        getCounter().i2l();
    }

    /**
     * This method is called when I2S was executed.
     */
    public static void i2s(){
        getCounter().i2s();
    }

    /**
     * This method is called when IADD was executed.
     */
    public static void iadd(){
        getCounter().iadd();
    }

    /**
     * This method is called when IALOAD was executed.
     */
    public static void iaload(){
        getCounter().iaload();
    }

    /**
     * This method is called when IAND was executed.
     */
    public static void iand(){
        getCounter().iand();
    }

    /**
     * This method is called when IASTORE was executed.
     */
    public static void iastore(){
        getCounter().iastore();
    }

    /**
     * This method is called when ICONST_M1 was executed.
     */
    public static void iconstM1(){
        getCounter().iconstM1();
    }

    /**
     * This method is called when ICONST_0 was executed.
     */
    public static void iconst0(){
        getCounter().iconst0();
    }

    /**
     * This method is called when ICONST_1 was executed.
     */
    public static void iconst1(){
        getCounter().iconst1();
    }

    /**
     * This method is called when ICONST_2 was executed.
     */
    public static void iconst2(){
        getCounter().iconst2();
    }

    /**
     * This method is called when ICONST_3 was executed.
     */
    public static void iconst3(){
        getCounter().iconst3();
    }

    /**
     * This method is called when ICONST_4 was executed.
     */
    public static void iconst4(){
        getCounter().iconst4();
    }

    /**
     * This method is called when ICONST_5 was executed.
     */
    public static void iconst5(){
        getCounter().iconst5();
    }

    /**
     * This method is called when IDIV was executed.
     */
    public static void idiv(){
        getCounter().idiv();
    }

    /**
     * This method is called when IFACMPEQ was executed.
     */
    public static void ifAcmpeq(){
        getCounter().ifAcmpeq();
    }

    /**
     * This method is called when IFACMPNE was executed.
     */
    public static void ifAcmpne(){
        getCounter().ifAcmpne();
    }

    /**
     * This method is called when IFICMPEQ was executed.
     */
    public static void ifIcmpeq(){
        getCounter().ifIcmpeq();
    }

    /**
     * This method is called when IFICMPGE was executed.
     */
    public static void ifIcmpge(){
        getCounter().ifIcmpge();
    }

    /**
     * This method is called when IFICMPGT was executed.
     */
    public static void ifIcmpgt(){
        getCounter().ifIcmpgt();
    }

    /**
     * This method is called when IFICMPLE was executed.
     */
    public static void ifIcmple(){
        getCounter().ifIcmple();
    }

    /**
     * This method is called when IFICMPLT was executed.
     */
    public static void ifIcmplt(){
        getCounter().ifIcmplt();
    }

    /**
     * This method is called when IFICMPNE was executed.
     */
    public static void ifIcmpne(){
        getCounter().ifIcmpne();
    }

    /**
     * This method is called when IFEQ was executed.
     */
    public static void ifeq(){
        getCounter().ifeq();
    }

    /**
     * This method is called when IFGE was executed.
     */
    public static void ifge(){
        getCounter().ifge();
    }

    /**
     * This method is called when IFGT was executed.
     */
    public static void ifgt(){
        getCounter().ifgt();
    }

    /**
     * This method is called when IFLE was executed.
     */
    public static void ifle(){
        getCounter().ifle();
    }

    /**
     * This method is called when IFLT was executed.
     */
    public static void iflt(){
        getCounter().iflt();
    }

    /**
     * This method is called when IFNE was executed.
     */
    public static void ifne(){
        getCounter().ifne();
    }

    /**
     * This method is called when IFNONNULL was executed.
     */
    public static void ifnonnull(){
        getCounter().ifnonnull();
    }

    /**
     * This method is called when IFNULL was executed.
     */
    public static void ifnull(){
        getCounter().ifnull();
    }

    /**
     * This method is called when IINC was executed.
     */
    public static void iinc(){
        getCounter().iinc();
    }

    /**
     * This method is called when ILOAD was executed.
     */
    public static void iload(){
        getCounter().iload();
    }

    /**
     * This method is called when IMPDEP was executed.
     */
    public static void impdep(){
        getCounter().impdep();
    }

    /**
     * This method is called when IMUL was executed.
     */
    public static void imul(){
        getCounter().imul();
    }

    /**
     * This method is called when INEG was executed.
     */
    public static void ineg(){
        getCounter().ineg();
    }

    /**
     * This method is called when INSTANCEOF was executed.
     */
    public static void instanceofInsn(){
        getCounter().instanceofInsn();
    }

    /**
     * This method is called when INVOKEINTERFACE was executed.
     */
    public static void invokeinterface(){
        getCounter().invokeinterface();
    }

    /**
     * This method is called when INVOKENONVIRTUAL was executed.
     */
    public static void invokenonvirtual(){
        getCounter().invokenonvirtual();
    }

    /**
     * This method is called when INVOKESPECIAL was executed.
     */
    public static void invokespecial(){
        getCounter().invokespecial();
    }

    /**
     * This method is called when INVOKESTATIC was executed.
     */
    public static void invokestatic(){
        getCounter().invokestatic();
    }

    /**
     * This method is called when INVOKESUPER was executed.
     */
    public static void invokesuper(){
        getCounter().invokesuper();
    }

    /**
     * This method is called when INVOKEVIRTUAL was executed.
     */
    public static void invokevirtual(){
        getCounter().invokevirtual();
    }

    /**
     * This method is called when INVOKEVIRTUALOBJECT was executed.
     */
    public static void invokevirtualobject(){
        getCounter().invokevirtualobject();
    }

    /**
     * This method is called when IOR was executed.
     */
    public static void ior(){
        getCounter().ior();
    }

    /**
     * This method is called when IREM was executed.
     */
    public static void irem(){
        getCounter().irem();
    }

    /**
     * This method is called when IRETURN was executed.
     */
    public static void ireturn(){
        getCounter().ireturn();
        returnMethod();
    }

    /**
     * This method is called when ISHL was executed.
     */
    public static void ishl(){
        getCounter().ishl();
    }

    /**
     * This method is called when ISHR was executed.
     */
    public static void ishr(){
        getCounter().ishr();
    }

    /**
     * This method is called when ISTORE was executed.
     */
    public static void istore(){
        getCounter().istore();
    }

    /**
     * This method is called when ISUB was executed.
     */
    public static void isub(){
        getCounter().isub();
    }

    /**
     * This method is called when IUSHR was executed.
     */
    public static void iushr(){
        getCounter().iushr();
    }

    /**
     * This method is called when IXOR was executed.
     */
    public static void ixor(){
        getCounter().ixor();
    }

    /**
     * This method is called when JSR was executed.
     */
    public static void jsr(){
        getCounter().jsr();
    }

    /**
     * This method is called when L2D was executed.
     */
    public static void l2d(){
        getCounter().l2d();
    }

    /**
     * This method is called when L2F was executed.
     */
    public static void l2f(){
        getCounter().l2f();
    }

    /**
     * This method is called when L2I was executed.
     */
    public static void l2i(){
        getCounter().l2i();
    }

    /**
     * This method is called when LADD was executed.
     */
    public static void ladd(){
        getCounter().ladd();
    }

    /**
     * This method is called when LALOAD was executed.
     */
    public static void laload(){
        getCounter().laload();
    }

    /**
     * This method is called when LAND was executed.
     */
    public static void land(){
        getCounter().land();
    }

    /**
     * This method is called when LASTORE was executed.
     */
    public static void lastore(){
        getCounter().lastore();
    }

    /**
     * This method is called when LCMP was executed.
     */
    public static void lcmp(){
        getCounter().lcmp();
    }

    /**
     * This method is called when LCONST_0 was executed.
     */
    public static void lconst0(){
        getCounter().lconst0();
    }

    /**
     * This method is called when LCONST_1 was executed.
     */
    public static void lconst1(){
        getCounter().lconst1();
    }

    /**
     * This method is called when LDC was executed.
     */
    public static void ldc(){
        getCounter().ldc();
    }

    /**
     * This method is called when LDC2 was executed.
     */
    public static void ldc2(){
        getCounter().ldc2();
    }

    /**
     * This method is called when LDIV was executed.
     */
    public static void ldiv(){
        getCounter().ldiv();
    }

    /**
     * This method is called when LLOAD was executed.
     */
    public static void lload(){
        getCounter().lload();
    }

    /**
     * This method is called when LMUL was executed.
     */
    public static void lmul(){
        getCounter().lmul();
    }

    /**
     * This method is called when LNEG was executed.
     */
    public static void lneg(){
        getCounter().lneg();
    }

    /**
     * This method is called when LOOKUPSWITCH was executed.
     */
    public static void lookupswitch(){
        getCounter().lookupswitch();
    }

    /**
     * This method is called when LOR was executed.
     */
    public static void lor(){
        getCounter().lor();
    }

    /**
     * This method is called when LREM was executed.
     */
    public static void lrem(){
        getCounter().lrem();
    }

    /**
     * This method is called when LRETURN was executed.
     */
    public static void lreturn(){
        getCounter().lreturn();
        returnMethod();
    }

    /**
     * This method is called when LSHL was executed.
     */
    public static void lshl(){
        getCounter().lshl();
    }

    /**
     * This method is called when LSHR was executed.
     */
    public static void lshr(){
        getCounter().lshr();
    }

    /**
     * This method is called when LSTORE was executed.
     */
    public static void lstore(){
        getCounter().lstore();
    }

    /**
     * This method is called when LSUB was executed.
     */
    public static void lsub(){
        getCounter().lsub();
    }

    /**
     * This method is called when LUSHR was executed.
     */
    public static void lushr(){
        getCounter().lushr();
    }

    /**
     * This method is called when LXOR was executed.
     */
    public static void lxor(){
        getCounter().lxor();
    }

    /**
     * This method is called when MONITORENTER was executed.
     */
    public static void monitorenter(){
        getCounter().monitorenter();
    }

    /**
     * This method is called when MONITOREXIT was executed.
     */
    public static void monitorexit(){
        getCounter().monitorexit();
    }

    /**
     * This method is called when MULTIANEWARRAY was executed.
     */
    public static void multianewarray(){
        getCounter().multianewarray();
    }

    /**
     * This method is called when NEW was executed.
     */
    public static void newInsn(){
        getCounter().newInsn();
    }

    /**
     * This method is called when NEWARRAY was executed.
     */
    public static void newarray(){
        getCounter().newarray();
    }

    /**
     * This method is called when NOMETHOD was executed.
     */
    public static void noMethod(){
        getCounter().noMethod();
    }

    /**
     * This method is called when NOP was executed.
     */
    public static void nop(){
        getCounter().nop();
    }

    /**
     * This method is called when POP was executed.
     */
    public static void pop(){
        getCounter().pop();
    }

    /**
     * This method is called when POP2 was executed.
     */
    public static void pop2(){
        getCounter().pop2();
    }

    /**
     * This method is called when PUTFIELD was executed.
     */
    public static void putfield(){
        getCounter().putfield();
    }

    /**
     * This method is called when PUTFIELD2 was executed.
     */
    public static void putfield2(){
        getCounter().putfield2();
    }

    /**
     * This method is called when PUTSTATIC was executed.
     */
    public static void putstatic(){
        getCounter().putstatic();
    }

    /**
     * This method is called when PUTSTATIC2 was executed.
     */
    public static void putstatic2(){
        getCounter().putstatic2();
    }

    /**
     * This method is called when RET was executed.
     */
    public static void ret(){
        getCounter().ret();
    }

    /**
     * This method is called when RETURN was executed.
     */
    public static void returnInsn(){
        getCounter().returnInsn();
        returnMethod();
    }

    /**
     * This method is called when SALOAD was executed.
     */
    public static void saload(){
        getCounter().saload();
    }

    /**
     * This method is called when SASTORE was executed.
     */
    public static void sastore(){
        getCounter().sastore();
    }

    /**
     * This method is called when SIPUSH was executed.
     */
    public static void sipush(){
        getCounter().sipush();
    }

    /**
     * This method is called when SWAP was executed.
     */
    public static void swap(){
        getCounter().swap();
    }

    /**
     * This method is called when TABLESWITCH was executed.
     */
    public static void tableswitch(){
        getCounter().tableswitch();
    }
}