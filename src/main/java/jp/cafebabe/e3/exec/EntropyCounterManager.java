package jp.cafebabe.e3.exec;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ServiceLoader;

/**
 * Management class for {@link EntropyCounter <code>EntropyCounter</code>}.
 * @author Haruaki Tamada
 */
public final class EntropyCounterManager{
    private static final EntropyCounterManager MANAGER =
        new EntropyCounterManager();
    private EntropyCounter currentMethod = new MethodEntropyCounter("", "");
    private List<EntropyCounter> stack = new ArrayList<EntropyCounter>();
    private List<EntropyCounter> executionList =
        new ArrayList<EntropyCounter>();

    private EntropyCounterManager(){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                summarize();
            }
        });
    }

    /**
     * This method returns singleton object of this class.
     * @return singleton object of this class.
     */
    public static EntropyCounterManager getInstance(){
        return MANAGER;
    }

    public static void entryMethod(final String className,
                                   final String methodName){
        // manager.enterMethod(className, methodName);
    }

    public static void returnMethod(){
        // manager.exitMethod();
    }

    /**
     * <p>
     * 各メソッドごとに実行されるインストラクションを収集したいが，
     * スタックの積み下ろしにバグがあるため，今のところ，未対応．
     * </p><p>
     * 将来的に実行されたメソッドごとに実行されたインストラクションを収
     * 集できるようにしたい．
     * </p>
     * @param className クラス名
     * @param methodName メソッド名
     */
    @SuppressWarnings("unused")
    private void enterMethod(final String className, final String methodName){
        MethodEntropyCounter method =
            new MethodEntropyCounter(className, methodName);
        stack.add(method);
        executionList.add(method);
        currentMethod = method;
    }

    /**
     * <p>
     * 各メソッドごとに実行されるインストラクションを収集したいが，
     * スタックの積み下ろしにバグがあるため，今のところ，未対応．
     * </p><p>
     * 将来的に実行されたメソッドごとに実行されたインストラクション
     * を収集できるようにしたい．
     * </p>
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

    public synchronized void summarize(){
        OpcodeManager manager = OpcodeManager.getInstance();
        byte[] sequence = new byte[currentMethod.getSize()];

        Map<Integer, Integer> opcodeCounter =
            printExecutionTrace(manager, sequence);
        printEntropy(manager, opcodeCounter);
        calculateKolmogorov(sequence);
    }

    private Map<Integer, Integer> printExecutionTrace(OpcodeManager manager,
                                                      byte[] sequence){
        Map<Integer, Integer> opcodeCounter = new TreeMap<Integer, Integer>();
        int index = 0;
        System.out.println(
            "############# execution trace (opcode,name) ###############"
        );

        for(Integer opcode: currentMethod){
            System.out.print(opcode);
            System.out.println("," + manager.getName(opcode));
            Integer count = opcodeCounter.get(opcode);
            if(count == null){
                count = Integer.valueOf(1);
            }
            else{
                count = Integer.valueOf(count.intValue() + 1);
            }
            opcodeCounter.put(opcode, count);

            sequence[index] = (byte)(opcode & 0xff);
            index++;
        }
        return opcodeCounter;
    }

    private void printEntropy(OpcodeManager manager,
                              Map<Integer, Integer> opcodeCounter){
        // 出現した命令を基にエントロピーを計算する．
        double entropy = 0d;
        // JVMに定義されている全ての命令を基にエントロピーを計算する．
        double entropy2 = 0d;
        double log2 = Math.log(2);

        System.out.println(
            "######### frequency of trace (opcode,name,count) ##########"
        );
        for(Map.Entry<Integer, Integer> entry: opcodeCounter.entrySet()){
            String name = manager.getName(entry.getKey());
            int opcode = entry.getKey();
            int count = entry.getValue();
            System.out.printf("%d,%s,%d%n", opcode, name, count);

            double probability = (double)count / opcodeCounter.size();
            double probability2 = (double)count / manager.getSize();

            entropy += -1 * probability * (Math.log(probability) / log2);
            entropy2 += -1 * probability2 * (Math.log(probability2) / log2);
        }

        System.out.println(
            "######################### entropy #########################"
        );
        System.out.println(entropy);
        System.out.println(entropy2);
    }

    private void calculateKolmogorov(byte[] sequence){
        System.out.println(
            "#### kolmogorov distance (algorithm,after,before,rate) ####"
        );
        ServiceLoader<KolmogorovCalculator> loader =
            ServiceLoader.load(KolmogorovCalculator.class);

        for(KolmogorovCalculator calculator: loader){
            System.out.println(calculator.getSummary(sequence));
        }
    }

    private static EntropyCounter getCounter(){
        return MANAGER.currentMethod;
    }

    /**
     * This method called when AALOAD is executed.
     */
    public static void aaload(){
        getCounter().aaload();
    }

    /**
     * This method called when AASTORE is executed.
     */
    public static void aastore(){
        getCounter().aastore();
    }

    /**
     * This method called when ACONST_NULL is executed.
     */
    public static void aconstNull(){
        getCounter().aconstNull();
    }

    /**
     * This method called when ALOAD is executed.
     */
    public static void aload(){
        getCounter().aload();
    }

    /**
     * This method called when ANEWARRAY is executed.
     */
    public static void anewarray(){
        getCounter().anewarray();
    }

    /**
     * This method called when ARETURN is executed.
     */
    public static void areturn(){
        getCounter().areturn();
        returnMethod();
    }

    /**
     * This method called when ARRAYLENGTH is executed.
     */
    public static void arraylength(){
        getCounter().arraylength();
    }

    /**
     * This method called when ASTORE is executed.
     */
    public static void astore(){
        getCounter().astore();
    }

    /**
     * This method called when ATHROW is executed.
     */
    public static void athrow(){
        getCounter().athrow();
    }

    /**
     * This method called when BALOAD is executed.
     */
    public static void baload(){
        getCounter().baload();
    }

    /**
     * This method called when BASTORE is executed.
     */
    public static void bastore(){
        getCounter().bastore();
    }

    /**
     * This method called when BIPUSH is executed.
     */
    public static void bipush(){
        getCounter().bipush();
    }

    /**
     * This method called when BREAKPOINT is executed.
     */
    public static void breakpoint(){
        getCounter().breakpoint();
    }

    /**
     * This method called when CALOAD is executed.
     */
    public static void caload(){
        getCounter().caload();
    }

    /**
     * This method called when CASTORE is executed.
     */
    public static void castore(){
        getCounter().castore();
    }

    /**
     * This method called when CHECKCAST is executed.
     */
    public static void checkcast(){
        getCounter().checkcast();
    }

    /**
     * This method called when D2F is executed.
     */
    public static void d2f(){
        getCounter().d2f();
    }

    /**
     * This method called when D2I is executed.
     */
    public static void d2i(){
        getCounter().d2i();
    }

    /**
     * This method called when D2L is executed.
     */
    public static void d2l(){
        getCounter().d2l();
    }

    /**
     * This method called when DADD is executed.
     */
    public static void dadd(){
        getCounter().dadd();
    }

    /**
     * This method called when DALOAD is executed.
     */
    public static void daload(){
        getCounter().daload();
    }

    /**
     * This method called when DASTORE is executed.
     */
    public static void dastore(){
        getCounter().dastore();
    }

    /**
     * This method called when DCMPG is executed.
     */
    public static void dcmpg(){
        getCounter().dcmpg();
    }

    /**
     * This method called when DCMPL is executed.
     */
    public static void dcmpl(){
        getCounter().dcmpl();
    }

    /**
     * This method called when DCONST_0 is executed.
     */
    public static void dconst0(){
        getCounter().dconst0();
    }

    /**
     * This method called when DCONST_1 is executed.
     */
    public static void dconst1(){
        getCounter().dconst1();
    }

    /**
     * This method called when DDIV is executed.
     */
    public static void ddiv(){
        getCounter().ddiv();
    }

    /**
     * This method called when DLOAD is executed.
     */
    public static void dload(){
        getCounter().dload();
    }

    /**
     * This method called when DMUL is executed.
     */
    public static void dmul(){
        getCounter().dmul();
    }

    /**
     * This method called when DNEG is executed.
     */
    public static void dneg(){
        getCounter().dneg();
    }

    /**
     * This method called when DREM is executed.
     */
    public static void drem(){
        getCounter().drem();
    }

    /**
     * This method called when DRETURN is executed.
     */
    public static void dreturn(){
        getCounter().dreturn();
        returnMethod();
    }

    /**
     * This method called when DSTORE is executed.
     */
    public static void dstore(){
        getCounter().dstore();
    }

    /**
     * This method called when DSUB is executed.
     */
    public static void dsub(){
        getCounter().dsub();
    }

    /**
     * This method called when DUP is executed.
     */
    public static void dup(){
        getCounter().dup();
    }

    /**
     * This method called when DUP2 is executed.
     */
    public static void dup2(){
        getCounter().dup2();
    }

    /**
     * This method called when DUP2_X1 is executed.
     */
    public static void dup2X1(){
        getCounter().dup2X1();
    }

    /**
     * This method called when DUP2_X2 is executed.
     */
    public static void dup2X2(){
        getCounter().dup2X2();
    }

    /**
     * This method called when DUP_X1 is executed.
     */
    public static void dupX1(){
        getCounter().dupX1();
    }

    /**
     * This method called when DUP_X2 is executed.
     */
    public static void dupX2(){
        getCounter().dupX2();
    }

    /**
     * This method called when F2D is executed.
     */
    public static void f2d(){
        getCounter().f2d();
    }

    /**
     * This method called when F2I is executed.
     */
    public static void f2i(){
        getCounter().f2i();
    }

    /**
     * This method called when F2L is executed.
     */
    public static void f2l(){
        getCounter().f2l();
    }

    /**
     * This method called when FADD is executed.
     */
    public static void fadd(){
        getCounter().fadd();
    }

    /**
     * This method called when FALOAD is executed.
     */
    public static void faload(){
        getCounter().faload();
    }

    /**
     * This method called when FASTORE is executed.
     */
    public static void fastore(){
        getCounter().fastore();
    }

    /**
     * This method called when FCMPG is executed.
     */
    public static void fcmpg(){
        getCounter().fcmpg();
    }

    /**
     * This method called when FCMPL is executed.
     */
    public static void fcmpl(){
        getCounter().fcmpl();
    }

    /**
     * This method called when FCONST_0 is executed.
     */
    public static void fconst0(){
        getCounter().fconst0();
    }

    /**
     * This method called when FCONST_1 is executed.
     */
    public static void fconst1(){
        getCounter().fconst1();
    }

    /**
     * This method called when FCONST_2 is executed.
     */
    public static void fconst2(){
        getCounter().fconst2();
    }

    /**
     * This method called when FDIV is executed.
     */
    public static void fdiv(){
        getCounter().fdiv();
    }

    /**
     * This method called when FLOAD is executed.
     */
    public static void fload(){
        getCounter().fload();
    }

    /**
     * This method called when FMUL is executed.
     */
    public static void fmul(){
        getCounter().fmul();
    }

    /**
     * This method called when FNEG is executed.
     */
    public static void fneg(){
        getCounter().fneg();
    }

    /**
     * This method called when FREM is executed.
     */
    public static void frem(){
        getCounter().frem();
    }

    /**
     * This method called when FRETURN is executed.
     */
    public static void freturn(){
        getCounter().freturn();
        returnMethod();
    }

    /**
     * This method called when FSTORE is executed.
     */
    public static void fstore(){
        getCounter().fstore();
    }

    /**
     * This method called when FSUB is executed.
     */
    public static void fsub(){
        getCounter().fsub();
    }

    /**
     * This method called when GETFIELD is executed.
     */
    public static void getfield(){
        getCounter().getfield();
    }

    /**
     * This method called when GETFIELD2 is executed.
     */
    public static void getfield2(){
        getCounter().getfield2();
    }

    /**
     * This method called when GETSTATIC is executed.
     */
    public static void getstatic(){
        getCounter().getstatic();
    }

    /**
     * This method called when GETSTATIC2 is executed.
     */
    public static void getstatic2(){
        getCounter().getstatic2();
    }

    /**
     * This method called when GOTO is executed.
     */
    public static void gotoInsn(){
        getCounter().gotoInsn();
    }

    /**
     * This method called when I2B is executed.
     */
    public static void i2b(){
        getCounter().i2b();
    }

    /**
     * This method called when I2C is executed.
     */
    public static void i2c(){
        getCounter().i2c();
    }

    /**
     * This method called when I2D is executed.
     */
    public static void i2d(){
        getCounter().i2d();
    }

    /**
     * This method called when I2F is executed.
     */
    public static void i2f(){
        getCounter().i2f();
    }

    /**
     * This method called when I2L is executed.
     */
    public static void i2l(){
        getCounter().i2l();
    }

    /**
     * This method called when I2S is executed.
     */
    public static void i2s(){
        getCounter().i2s();
    }

    /**
     * This method called when IADD is executed.
     */
    public static void iadd(){
        getCounter().iadd();
    }

    /**
     * This method called when IALOAD is executed.
     */
    public static void iaload(){
        getCounter().iaload();
    }

    /**
     * This method called when IAND is executed.
     */
    public static void iand(){
        getCounter().iand();
    }

    /**
     * This method called when IASTORE is executed.
     */
    public static void iastore(){
        getCounter().iastore();
    }

    /**
     * This method called when ICONST_M1 is executed.
     */
    public static void iconstM1(){
        getCounter().iconstM1();
    }

    /**
     * This method called when ICONST_0 is executed.
     */
    public static void iconst0(){
        getCounter().iconst0();
    }

    /**
     * This method called when ICONST_1 is executed.
     */
    public static void iconst1(){
        getCounter().iconst1();
    }

    /**
     * This method called when ICONST_2 is executed.
     */
    public static void iconst2(){
        getCounter().iconst2();
    }

    /**
     * This method called when ICONST_3 is executed.
     */
    public static void iconst3(){
        getCounter().iconst3();
    }

    /**
     * This method called when ICONST_4 is executed.
     */
    public static void iconst4(){
        getCounter().iconst4();
    }

    /**
     * This method called when ICONST_5 is executed.
     */
    public static void iconst5(){
        getCounter().iconst5();
    }

    /**
     * This method called when IDIV is executed.
     */
    public static void idiv(){
        getCounter().idiv();
    }

    /**
     * This method called when IFACMPEQ is executed.
     */
    public static void ifAcmpeq(){
        getCounter().ifAcmpeq();
    }

    /**
     * This method called when IFACMPNE is executed.
     */
    public static void ifAcmpne(){
        getCounter().ifAcmpne();
    }

    /**
     * This method called when IFICMPEQ is executed.
     */
    public static void ifIcmpeq(){
        getCounter().ifIcmpeq();
    }

    /**
     * This method called when IFICMPGE is executed.
     */
    public static void ifIcmpge(){
        getCounter().ifIcmpge();
    }

    /**
     * This method called when IFICMPGT is executed.
     */
    public static void ifIcmpgt(){
        getCounter().ifIcmpgt();
    }

    /**
     * This method called when IFICMPLE is executed.
     */
    public static void ifIcmple(){
        getCounter().ifIcmple();
    }

    /**
     * This method called when IFICMPLT is executed.
     */
    public static void ifIcmplt(){
        getCounter().ifIcmplt();
    }

    /**
     * This method called when IFICMPNE is executed.
     */
    public static void ifIcmpne(){
        getCounter().ifIcmpne();
    }

    /**
     * This method called when IFEQ is executed.
     */
    public static void ifeq(){
        getCounter().ifeq();
    }

    /**
     * This method called when IFGE is executed.
     */
    public static void ifge(){
        getCounter().ifge();
    }

    /**
     * This method called when IFGT is executed.
     */
    public static void ifgt(){
        getCounter().ifgt();
    }

    /**
     * This method called when IFLE is executed.
     */
    public static void ifle(){
        getCounter().ifle();
    }

    /**
     * This method called when IFLT is executed.
     */
    public static void iflt(){
        getCounter().iflt();
    }

    /**
     * This method called when IFNE is executed.
     */
    public static void ifne(){
        getCounter().ifne();
    }

    /**
     * This method called when IFNONNULL is executed.
     */
    public static void ifnonnull(){
        getCounter().ifnonnull();
    }

    /**
     * This method called when IFNULL is executed.
     */
    public static void ifnull(){
        getCounter().ifnull();
    }

    /**
     * This method called when IINC is executed.
     */
    public static void iinc(){
        getCounter().iinc();
    }

    /**
     * This method called when ILOAD is executed.
     */
    public static void iload(){
        getCounter().iload();
    }

    /**
     * This method called when IMPDEP is executed.
     */
    public static void impdep(){
        getCounter().impdep();
    }

    /**
     * This method called when IMUL is executed.
     */
    public static void imul(){
        getCounter().imul();
    }

    /**
     * This method called when INEG is executed.
     */
    public static void ineg(){
        getCounter().ineg();
    }

    /**
     * This method called when INSTANCEOF is executed.
     */
    public static void instanceofInsn(){
        getCounter().instanceofInsn();
    }

    /**
     * This method called when INVOKEINTERFACE is executed.
     */
    public static void invokeinterface(){
        getCounter().invokeinterface();
    }

    /**
     * This method called when INVOKENONVIRTUAL is executed.
     */
    public static void invokenonvirtual(){
        getCounter().invokenonvirtual();
    }

    /**
     * This method called when INVOKESPECIAL is executed.
     */
    public static void invokespecial(){
        getCounter().invokespecial();
    }

    /**
     * This method called when INVOKESTATIC is executed.
     */
    public static void invokestatic(){
        getCounter().invokestatic();
    }

    /**
     * This method called when INVOKESUPER is executed.
     */
    public static void invokesuper(){
        getCounter().invokesuper();
    }

    /**
     * This method called when INVOKEVIRTUAL is executed.
     */
    public static void invokevirtual(){
        getCounter().invokevirtual();
    }

    /**
     * This method called when INVOKEVIRTUALOBJECT is executed.
     */
    public static void invokevirtualobject(){
        getCounter().invokevirtualobject();
    }

    /**
     * This method called when IOR is executed.
     */
    public static void ior(){
        getCounter().ior();
    }

    /**
     * This method called when IREM is executed.
     */
    public static void irem(){
        getCounter().irem();
    }

    /**
     * This method called when IRETURN is executed.
     */
    public static void ireturn(){
        getCounter().ireturn();
        returnMethod();
    }

    /**
     * This method called when ISHL is executed.
     */
    public static void ishl(){
        getCounter().ishl();
    }

    /**
     * This method called when ISHR is executed.
     */
    public static void ishr(){
        getCounter().ishr();
    }

    /**
     * This method called when ISTORE is executed.
     */
    public static void istore(){
        getCounter().istore();
    }

    /**
     * This method called when ISUB is executed.
     */
    public static void isub(){
        getCounter().isub();
    }

    /**
     * This method called when IUSHR is executed.
     */
    public static void iushr(){
        getCounter().iushr();
    }

    /**
     * This method called when IXOR is executed.
     */
    public static void ixor(){
        getCounter().ixor();
    }

    /**
     * This method called when JSR is executed.
     */
    public static void jsr(){
        getCounter().jsr();
    }

    /**
     * This method called when L2D is executed.
     */
    public static void l2d(){
        getCounter().l2d();
    }

    /**
     * This method called when L2F is executed.
     */
    public static void l2f(){
        getCounter().l2f();
    }

    /**
     * This method called when L2I is executed.
     */
    public static void l2i(){
        getCounter().l2i();
    }

    /**
     * This method called when LADD is executed.
     */
    public static void ladd(){
        getCounter().ladd();
    }

    /**
     * This method called when LALOAD is executed.
     */
    public static void laload(){
        getCounter().laload();
    }

    /**
     * This method called when LAND is executed.
     */
    public static void land(){
        getCounter().land();
    }

    /**
     * This method called when LASTORE is executed.
     */
    public static void lastore(){
        getCounter().lastore();
    }

    /**
     * This method called when LCMP is executed.
     */
    public static void lcmp(){
        getCounter().lcmp();
    }

    /**
     * This method called when LCONST_0 is executed.
     */
    public static void lconst0(){
        getCounter().lconst0();
    }

    /**
     * This method called when LCONST_1 is executed.
     */
    public static void lconst1(){
        getCounter().lconst1();
    }

    /**
     * This method called when LDC is executed.
     */
    public static void ldc(){
        getCounter().ldc();
    }

    /**
     * This method called when LDC2 is executed.
     */
    public static void ldc2(){
        getCounter().ldc2();
    }

    /**
     * This method called when LDIV is executed.
     */
    public static void ldiv(){
        getCounter().ldiv();
    }

    /**
     * This method called when LLOAD is executed.
     */
    public static void lload(){
        getCounter().lload();
    }

    /**
     * This method called when LMUL is executed.
     */
    public static void lmul(){
        getCounter().lmul();
    }

    /**
     * This method called when LNEG is executed.
     */
    public static void lneg(){
        getCounter().lneg();
    }

    /**
     * This method called when LOOKUPSWITCH is executed.
     */
    public static void lookupswitch(){
        getCounter().lookupswitch();
    }

    /**
     * This method called when LOR is executed.
     */
    public static void lor(){
        getCounter().lor();
    }

    /**
     * This method called when LREM is executed.
     */
    public static void lrem(){
        getCounter().lrem();
    }

    /**
     * This method called when LRETURN is executed.
     */
    public static void lreturn(){
        getCounter().lreturn();
        returnMethod();
    }

    /**
     * This method called when LSHL is executed.
     */
    public static void lshl(){
        getCounter().lshl();
    }

    /**
     * This method called when LSHR is executed.
     */
    public static void lshr(){
        getCounter().lshr();
    }

    /**
     * This method called when LSTORE is executed.
     */
    public static void lstore(){
        getCounter().lstore();
    }

    /**
     * This method called when LSUB is executed.
     */
    public static void lsub(){
        getCounter().lsub();
    }

    /**
     * This method called when LUSHR is executed.
     */
    public static void lushr(){
        getCounter().lushr();
    }

    /**
     * This method called when LXOR is executed.
     */
    public static void lxor(){
        getCounter().lxor();
    }

    /**
     * This method called when MONITORENTER is executed.
     */
    public static void monitorenter(){
        getCounter().monitorenter();
    }

    /**
     * This method called when MONITOREXIT is executed.
     */
    public static void monitorexit(){
        getCounter().monitorexit();
    }

    /**
     * This method called when MULTIANEWARRAY is executed.
     */
    public static void multianewarray(){
        getCounter().multianewarray();
    }

    /**
     * This method called when NEW is executed.
     */
    public static void newInsn(){
        getCounter().newInsn();
    }

    /**
     * This method called when NEWARRAY is executed.
     */
    public static void newarray(){
        getCounter().newarray();
    }

    /**
     * This method called when NOMETHOD is executed.
     */
    public static void noMethod(){
        getCounter().noMethod();
    }

    /**
     * This method called when NOP is executed.
     */
    public static void nop(){
        getCounter().nop();
    }

    /**
     * This method called when POP is executed.
     */
    public static void pop(){
        getCounter().pop();
    }

    /**
     * This method called when POP2 is executed.
     */
    public static void pop2(){
        getCounter().pop2();
    }

    /**
     * This method called when PUTFIELD is executed.
     */
    public static void putfield(){
        getCounter().putfield();
    }

    /**
     * This method called when PUTFIELD2 is executed.
     */
    public static void putfield2(){
        getCounter().putfield2();
    }

    /**
     * This method called when PUTSTATIC is executed.
     */
    public static void putstatic(){
        getCounter().putstatic();
    }

    /**
     * This method called when PUTSTATIC2 is executed.
     */
    public static void putstatic2(){
        getCounter().putstatic2();
    }

    /**
     * This method called when RET is executed.
     */
    public static void ret(){
        getCounter().ret();
    }

    /**
     * This method called when RETURN is executed.
     */
    public static void returnInsn(){
        getCounter().returnInsn();
        returnMethod();
    }

    /**
     * This method called when SALOAD is executed.
     */
    public static void saload(){
        getCounter().saload();
    }

    /**
     * This method called when SASTORE is executed.
     */
    public static void sastore(){
        getCounter().sastore();
    }

    /**
     * This method called when SIPUSH is executed.
     */
    public static void sipush(){
        getCounter().sipush();
    }

    /**
     * This method called when SWAP is executed.
     */
    public static void swap(){
        getCounter().swap();
    }

    /**
     * This method called when TABLESWITCH is executed.
     */
    public static void tableswitch(){
        getCounter().tableswitch();
    }
}