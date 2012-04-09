package jp.cafebabe.e3.exec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EntropyCounterManager{
    private static final EntropyCounterManager manager = new EntropyCounterManager();
    private EntropyCounter currentMethod = new MethodEntropyCounter("", "");
    private List<EntropyCounter> stack = new ArrayList<EntropyCounter>();
    private List<EntropyCounter> executionList = new ArrayList<EntropyCounter>();

    private EntropyCounterManager(){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                summarize();
            }
        });
    }

    public static EntropyCounterManager getInstance(){
        return manager;
    }

    public static void entryMethod(String className, String methodName){
        // manager.enterMethod(className, methodName);
    }

    public static void returnMethod(){
        // manager.exitMethod();
    }

    /**
     * 各メソッドごとに実行されるインストラクションを収集したいが，
     * スタックの積み下ろしにバグがあるため，今のところ，未対応．
     * 
     * 将来的に実行されたメソッドごとに実行されたインストラクションを収集できるようにしたい．
     * @param className
     * @param methodName
     */
    @SuppressWarnings("unused")
    private void enterMethod(String className, String methodName){
        MethodEntropyCounter method = new MethodEntropyCounter(className, methodName);
        stack.add(method);
        executionList.add(method);
        currentMethod = method;
    }

    /**
     * 各メソッドごとに実行されるインストラクションを収集したいが，
     * スタックの積み下ろしにバグがあるため，今のところ，未対応．
     * 
     * 将来的に実行されたメソッドごとに実行されたインストラクションを収集できるようにしたい．
     */
    @SuppressWarnings("unused")
    private void exitMethod(){
        stack.remove(stack.size() - 1);
        if(stack.size() > 0){
            currentMethod = stack.get(stack.size() - 1);
        }
        else{
            currentMethod = new EmptyMethodEntropyCounter();
        }
    }

    public void summarize(){
        Map<Integer, Integer> opcodeCounter = new TreeMap<Integer, Integer>();

        System.out.println("############### execution trace (opcode,name) ###############");
        for(Integer opcode: currentMethod){
            System.out.print(opcode);
            System.out.println("," + OpcodeManager.getInstance().getName(opcode));
            Integer count = opcodeCounter.get(opcode);
            if(count == null){
                count = new Integer(1);
            }
            else{
                count = new Integer(count.intValue() + 1);
            }
            opcodeCounter.put(opcode, count);
        }

        System.out.println("############### frequency of trace (opcode,name,freq) ###############");
        for(Map.Entry<Integer, Integer> entry: opcodeCounter.entrySet()){
            String name = OpcodeManager.getInstance().getName(entry.getKey());
            System.out.printf("%d,%s,%d%n", entry.getKey(), name, entry.getValue());
        }
    }

    private static EntropyCounter getCounter(){
        return manager.currentMethod;
    }

    public static void aaload(){
        getCounter().aaload();
    }

    public static void aastore(){
        getCounter().aastore();
    }

    public static void aconstNull(){
        getCounter().aconstNull();
    }

    public static void aload(){
        getCounter().aload();
    }

    public static void anewarray(){
        getCounter().anewarray();
    }

    public static void areturn(){
        getCounter().areturn();
        returnMethod();
    }

    public static void arraylength(){
        getCounter().arraylength();
    }

    public static void astore(){
        getCounter().astore();
    }

    public static void athrow(){
        getCounter().athrow();
    }

    public static void baload(){
        getCounter().baload();
    }

    public static void bastore(){
        getCounter().bastore();
    }

    public static void bipush(){
        getCounter().bipush();
    }

    public static void breakpoint(){
        getCounter().breakpoint();
    }

    public static void caload(){
        getCounter().caload();
    }

    public static void castore(){
        getCounter().castore();
    }

    public static void checkcast(){
        getCounter().checkcast();
    }

    public static void d2f(){
        getCounter().d2f();
    }

    public static void d2i(){
        getCounter().d2i();
    }

    public static void d2l(){
        getCounter().d2l();
    }

    public static void dadd(){
        getCounter().dadd();
    }

    public static void daload(){
        getCounter().daload();
    }

    public static void dastore(){
        getCounter().dastore();
    }

    public static void dcmpg(){
        getCounter().dcmpg();
    }

    public static void dcmpl(){
        getCounter().dcmpl();
    }

    public static void dconst0(){
        getCounter().dconst0();
    }

    public static void dconst1(){
        getCounter().dconst1();
    }

    public static void ddiv(){
        getCounter().ddiv();
    }

    public static void dload(){
        getCounter().dload();
    }

    public static void dmul(){
        getCounter().dmul();
    }

    public static void dneg(){
        getCounter().dneg();
    }

    public static void drem(){
        getCounter().drem();
    }

    public static void dreturn(){
        getCounter().dreturn();
        returnMethod();
    }

    public static void dstore(){
        getCounter().dstore();
    }

    public static void dsub(){
        getCounter().dsub();
    }

    public static void dup(){
        getCounter().dup();
    }

    public static void dup2(){
        getCounter().dup2();
    }

    public static void dup2X1(){
        getCounter().dup2X1();
    }

    public static void dup2X2(){
        getCounter().dup2X2();
    }

    public static void dupX1(){
        getCounter().dupX1();
    }

    public static void dupX2(){
        getCounter().dupX2();
    }

    public static void f2d(){
        getCounter().f2d();
    }

    public static void f2i(){
        getCounter().f2i();
    }

    public static void f2l(){
        getCounter().f2l();
    }

    public static void fadd(){
        getCounter().fadd();
    }

    public static void faload(){
        getCounter().faload();
    }

    public static void fastore(){
        getCounter().fastore();
    }

    public static void fcmpg(){
        getCounter().fcmpg();
    }

    public static void fcmpl(){
        getCounter().fcmpl();
    }

    public static void fconst0(){
        getCounter().fconst0();
    }

    public static void fconst1(){
        getCounter().fconst1();
    }

    public static void fconst2(){
        getCounter().fconst2();
    }

    public static void fdiv(){
        getCounter().fdiv();
    }

    public static void fload(){
        getCounter().fload();
    }

    public static void fmul(){
        getCounter().fmul();
    }

    public static void fneg(){
        getCounter().fneg();
    }

    public static void frem(){
        getCounter().frem();
    }

    public static void freturn(){
        getCounter().freturn();
        returnMethod();
    }

    public static void fstore(){
        getCounter().fstore();
    }

    public static void fsub(){
        getCounter().fsub();
    }

    public static void getfield(){
        getCounter().getfield();
    }

    public static void getfield2(){
        getCounter().getfield2();
    }

    public static void getstatic(){
        getCounter().getstatic();
    }

    public static void getstatic2(){
        getCounter().getstatic2();
    }

    public static void gotoInsn(){
        getCounter().gotoInsn();
    }

    public static void i2b(){
        getCounter().i2b();
    }

    public static void i2c(){
        getCounter().i2c();
    }

    public static void i2d(){
        getCounter().i2d();
    }

    public static void i2f(){
        getCounter().i2f();
    }

    public static void i2l(){
        getCounter().i2l();
    }

    public static void i2s(){
        getCounter().i2s();
    }

    public static void iadd(){
        getCounter().iadd();
    }

    public static void iaload(){
        getCounter().iaload();
    }

    public static void iand(){
        getCounter().iand();
    }

    public static void iastore(){
        getCounter().iastore();
    }

    public static void iconstM1(){
        getCounter().iconstM1();
    }

    public static void iconst0(){
        getCounter().iconst0();
    }

    public static void iconst1(){
        getCounter().iconst1();
    }

    public static void iconst2(){
        getCounter().iconst2();
    }

    public static void iconst3(){
        getCounter().iconst3();
    }

    public static void iconst4(){
        getCounter().iconst4();
    }

    public static void iconst5(){
        getCounter().iconst5();
    }

    public static void idiv(){
        getCounter().idiv();
    }

    public static void ifAcmpeq(){
        getCounter().ifAcmpeq();
    }

    public static void ifAcmpne(){
        getCounter().ifAcmpne();
    }

    public static void ifIcmpeq(){
        getCounter().ifIcmpeq();
    }

    public static void ifIcmpge(){
        getCounter().ifIcmpge();
    }

    public static void ifIcmpgt(){
        getCounter().ifIcmpgt();
    }

    public static void ifIcmple(){
        getCounter().ifIcmple();
    }

    public static void ifIcmplt(){
        getCounter().ifIcmplt();
    }

    public static void ifIcmpne(){
        getCounter().ifIcmpne();
    }

    public static void ifeq(){
        getCounter().ifeq();
    }

    public static void ifge(){
        getCounter().ifge();
    }

    public static void ifgt(){
        getCounter().ifgt();
    }

    public static void ifle(){
        getCounter().ifle();
    }

    public static void iflt(){
        getCounter().iflt();
    }

    public static void ifne(){
        getCounter().ifne();
    }

    public static void ifnonnull(){
        getCounter().ifnonnull();
    }

    public static void ifnull(){
        getCounter().ifnull();
    }

    public static void iinc(){
        getCounter().iinc();
    }

    public static void iload(){
        getCounter().iload();
    }

    public static void impdep(){
        getCounter().impdep();
    }

    public static void imul(){
        getCounter().imul();
    }

    public static void ineg(){
        getCounter().ineg();
    }

    public static void instanceofInsn(){
        getCounter().instanceofInsn();
    }

    public static void invokeinterface(){
        getCounter().invokeinterface();
    }

    public static void invokenonvirtual(){
        getCounter().invokenonvirtual();
    }

    public static void invokespecial(){
        getCounter().invokespecial();
    }

    public static void invokestatic(){
        getCounter().invokestatic();
    }

    public static void invokesuper(){
        getCounter().invokesuper();
    }

    public static void invokevirtual(){
        getCounter().invokevirtual();
    }

    public static void invokevirtualobject(){
        getCounter().invokevirtualobject();
    }

    public static void ior(){
        getCounter().ior();
    }

    public static void irem(){
        getCounter().irem();
    }

    public static void ireturn(){
        getCounter().ireturn();
        returnMethod();
    }

    public static void ishl(){
        getCounter().ishl();
    }

    public static void ishr(){
        getCounter().ishr();
    }

    public static void istore(){
        getCounter().istore();
    }

    public static void isub(){
        getCounter().isub();
    }

    public static void iushr(){
        getCounter().iushr();
    }

    public static void ixor(){
        getCounter().ixor();
    }

    public static void jsr(){
        getCounter().jsr();
    }

    public static void l2d(){
        getCounter().l2d();
    }

    public static void l2f(){
        getCounter().l2f();
    }

    public static void l2i(){
        getCounter().l2i();
    }

    public static void ladd(){
        getCounter().ladd();
    }

    public static void laload(){
        getCounter().laload();
    }

    public static void land(){
        getCounter().land();
    }

    public static void lastore(){
        getCounter().lastore();
    }

    public static void lcmp(){
        getCounter().lcmp();
    }

    public static void lconst0(){
        getCounter().lconst0();
    }

    public static void lconst1(){
        getCounter().lconst1();
    }

    public static void ldc(){
        getCounter().ldc();
    }

    public static void ldc2(){
        getCounter().ldc2();
    }

    public static void ldiv(){
        getCounter().ldiv();
    }

    public static void lload(){
        getCounter().lload();
    }

    public static void lmul(){
        getCounter().lmul();
    }

    public static void lneg(){
        getCounter().lneg();
    }

    public static void lookupswitch(){
        getCounter().lookupswitch();
    }

    public static void lor(){
        getCounter().lor();
    }

    public static void lrem(){
        getCounter().lrem();
    }

    public static void lreturn(){
        getCounter().lreturn();
        returnMethod();
    }

    public static void lshl(){
        getCounter().lshl();
    }

    public static void lshr(){
        getCounter().lshr();
    }

    public static void lstore(){
        getCounter().lstore();
    }

    public static void lsub(){
        getCounter().lsub();
    }

    public static void lushr(){
        getCounter().lushr();
    }

    public static void lxor(){
        getCounter().lxor();
    }

    public static void monitorenter(){
        getCounter().monitorenter();
    }

    public static void monitorexit(){
        getCounter().monitorexit();
    }

    public static void multianewarray(){
        getCounter().multianewarray();
    }

    public static void newMethod(){
        getCounter().newMethod();
    }

    public static void newarray(){
        getCounter().newarray();
    }

    public static void noMethod(){
        getCounter().noMethod();
    }

    public static void nop(){
        getCounter().nop();
    }

    public static void pop(){
        getCounter().pop();
    }

    public static void pop2(){
        getCounter().pop2();
    }

    public static void putfield(){
        getCounter().putfield();
    }

    public static void putfield2(){
        getCounter().putfield2();
    }

    public static void putstatic(){
        getCounter().putstatic();
    }

    public static void putstatic2(){
        getCounter().putstatic2();
    }

    public static void ret(){
        getCounter().ret();
    }

    public static void returnInsn(){
        getCounter().returnInsn();
        returnMethod();
    }

    public static void saload(){
        getCounter().saload();
    }

    public static void sastore(){
        getCounter().sastore();
    }

    public static void sipush(){
        getCounter().sipush();
    }

    public static void swap(){
        getCounter().swap();
    }

    public static void tableswitch(){
        getCounter().tableswitch();
    }
}