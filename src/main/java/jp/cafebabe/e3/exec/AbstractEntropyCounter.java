package jp.cafebabe.e3.exec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class AbstractEntropyCounter implements EntropyCounter{
    private List<Integer> opcodes = new ArrayList<Integer>();

    /**
     * Opcodes一覧．
     */
    public Iterator<Integer> iterator(){
        return opcodes.iterator();
    }

    public final int getSize(){
        return opcodes.size();
    }

    @Override
    public void summarize(){
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for(Integer opcode: this){
            System.out.println(opcode);
            Integer count = map.get(opcode);
            if(count == null){
                count = new Integer(1);
            }
            else{
                count = new Integer(count.intValue() + 1);
            }
            map.put(opcode, count);
        }

        System.out.println("opcode: count");
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            System.out.printf("%4d: %4d%n", entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void aaload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aaload"));
    }

    @Override
    public void aastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aastore"));
    }

    @Override
    public void aconstNull(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aconst_null"));
    }

    @Override
    public void aload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aload"));
    }

    @Override
    public void anewarray(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("anewarray"));
    }

    @Override
    public void areturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("areturn"));
    }

    @Override
    public void arraylength(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("arraylength"));
    }

    @Override
    public void astore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("astore"));
    }

    @Override
    public void athrow(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("athrow"));
    }

    @Override
    public void baload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("baload"));
    }

    @Override
    public void bastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("bastore"));
    }

    @Override
    public void bipush(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("bipush"));
    }

    @Override
    public void breakpoint(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("breakpoint"));
    }

    @Override
    public void caload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("caload"));
    }

    @Override
    public void castore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("castore"));
    }

    @Override
    public void checkcast(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("checkcast"));
    }

    @Override
    public void d2f(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("d2f"));
    }

    @Override
    public void d2i(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("d2i"));
    }

    @Override
    public void d2l(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("d2l"));
    }

    @Override
    public void dadd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dadd"));
    }

    @Override
    public void daload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("daload"));
    }

    @Override
    public void dastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dastore"));
    }

    @Override
    public void dcmpg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dcmpg"));
    }

    @Override
    public void dcmpl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dcmpl"));
    }

    @Override
    public void dconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dconst_0"));
    }

    @Override
    public void dconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dconst_1"));
    }

    @Override
    public void ddiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ddiv"));
    }

    @Override
    public void dload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dload"));
    }

    @Override
    public void dmul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dmul"));
    }

    @Override
    public void dneg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dneg"));
    }

    @Override
    public void drem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("drem"));
    }

    @Override
    public void dreturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dreturn"));
    }

    @Override
    public void dstore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dstore"));
    }

    @Override
    public void dsub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dsub"));
    }

    @Override
    public void dup(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup"));
    }

    @Override
    public void dup2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup2"));
    }

    @Override
    public void dup2X1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup2_x1"));
    }

    @Override
    public void dup2X2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup2_x2"));
    }

    @Override
    public void dupX1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup_x1"));
    }

    @Override
    public void dupX2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup_x2"));
    }

    @Override
    public void f2d(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("f2d"));
    }

    @Override
    public void f2i(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("f2i"));
    }

    @Override
    public void f2l(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("f2l"));
    }

    @Override
    public void fadd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fadd"));
    }

    @Override
    public void faload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("faload"));
    }

    @Override
    public void fastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fastore"));
    }

    @Override
    public void fcmpg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fcmpg"));
    }

    @Override
    public void fcmpl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fcmpl"));
    }

    @Override
    public void fconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fconst_0"));
    }

    @Override
    public void fconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fconst_1"));
    }

    @Override
    public void fconst2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fconst_2"));
    }

    @Override
    public void fdiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fdiv"));
    }

    @Override
    public void fload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fload"));
    }

    @Override
    public void fmul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fmul"));
    }

    @Override
    public void fneg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fneg"));
    }

    @Override
    public void frem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("frem"));
    }

    @Override
    public void freturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("freturn"));
    }

    @Override
    public void fstore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fstore"));
    }

    @Override
    public void fsub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fsub"));
    }

    @Override
    public void getfield(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getfield"));
    }

    @Override
    public void getfield2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getfield2"));
    }

    @Override
    public void getstatic(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getstatic"));
    }

    @Override
    public void getstatic2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getstatic2"));
    }

    @Override
    public void gotoInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("goto"));
    }

    @Override
    public void i2b(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2b"));
    }

    @Override
    public void i2c(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2c"));
    }

    @Override
    public void i2d(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2d"));
    }

    @Override
    public void i2f(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2f"));
    }

    @Override
    public void i2l(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2l"));
    }

    @Override
    public void i2s(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2s"));
    }

    @Override
    public void iadd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iadd"));
    }

    @Override
    public void iaload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iaload"));
    }

    @Override
    public void iand(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iand"));
    }

    @Override
    public void iastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iastore"));
    }

    @Override
    public void iconstM1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_m1"));
    }

    @Override
    public void iconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_0"));
    }

    @Override
    public void iconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_1"));
    }

    @Override
    public void iconst2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_2"));
    }

    @Override
    public void iconst3(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_3"));
    }

    @Override
    public void iconst4(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_4"));
    }

    @Override
    public void iconst5(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_5"));
    }

    @Override
    public void idiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("idiv"));
    }

    @Override
    public void ifAcmpeq(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_acmpeq"));
    }

    @Override
    public void ifAcmpne(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_acmpne"));
    }

    @Override
    public void ifIcmpeq(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpeq"));
    }

    @Override
    public void ifIcmpge(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpge"));
    }

    @Override
    public void ifIcmpgt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpgt"));
    }

    @Override
    public void ifIcmple(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmple"));
    }

    @Override
    public void ifIcmplt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmplt"));
    }

    @Override
    public void ifIcmpne(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpne"));
    }

    @Override
    public void ifeq(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifeq"));
    }

    @Override
    public void ifge(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifge"));
    }

    @Override
    public void ifgt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifgt"));
    }

    @Override
    public void ifle(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifle"));
    }

    @Override
    public void iflt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iflt"));
    }

    @Override
    public void ifne(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifne"));
    }

    @Override
    public void ifnonnull(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifnonnull"));
    }

    @Override
    public void ifnull(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifnull"));
    }

    @Override
    public void iinc(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iinc"));
    }

    @Override
    public void iload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iload"));
    }

    @Override
    public void impdep(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("impdep"));
    }

    @Override
    public void imul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("imul"));
    }

    @Override
    public void ineg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ineg"));
    }

    @Override
    public void instanceofInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("instanceof"));
    }

    @Override
    public void invokeinterface(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokeinterface"));
    }

    @Override
    public void invokenonvirtual(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokenonvirtual"));
    }

    @Override
    public void invokespecial(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokespecial"));
    }

    @Override
    public void invokestatic(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokestatic"));
    }

    @Override
    public void invokesuper(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokesuper"));
    }

    @Override
    public void invokevirtual(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokevirtual"));
    }

    @Override
    public void invokevirtualobject(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokevirtualobject"));
    }

    @Override
    public void ior(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ior"));
    }

    @Override
    public void irem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("irem"));
    }

    @Override
    public void ireturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ireturn"));
    }

    @Override
    public void ishl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ishl"));
    }

    @Override
    public void ishr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ishr"));
    }

    @Override
    public void istore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("istore"));
    }

    @Override
    public void isub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("isub"));
    }

    @Override
    public void iushr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iushr"));
    }

    @Override
    public void ixor(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ixor"));
    }

    @Override
    public void jsr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("jsr"));
    }

    @Override
    public void l2d(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("l2d"));
    }

    @Override
    public void l2f(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("l2f"));
    }

    @Override
    public void l2i(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("l2i"));
    }

    @Override
    public void ladd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ladd"));
    }

    @Override
    public void laload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("laload"));
    }

    @Override
    public void land(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("land"));
    }

    @Override
    public void lastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lastore"));
    }

    @Override
    public void lcmp(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lcmp"));
    }

    @Override
    public void lconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lconst_0"));
    }

    @Override
    public void lconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lconst_1"));
    }

    @Override
    public void ldc(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ldc"));
    }

    @Override
    public void ldc2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ldc2"));
    }

    @Override
    public void ldiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ldiv"));
    }

    @Override
    public void lload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lload"));
    }

    @Override
    public void lmul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lmul"));
    }

    @Override
    public void lneg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lneg"));
    }

    @Override
    public void lookupswitch(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lookupswitch"));
    }

    @Override
    public void lor(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lor"));
    }

    @Override
    public void lrem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lrem"));
    }

    @Override
    public void lreturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lreturn"));
    }

    @Override
    public void lshl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lshl"));
    }

    @Override
    public void lshr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lshr"));
    }

    @Override
    public void lstore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lstore"));
    }

    @Override
    public void lsub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lsub"));
    }

    @Override
    public void lushr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lushr"));
    }

    @Override
    public void lxor(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lxor"));
    }

    @Override
    public void monitorenter(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("monitorenter"));
    }

    @Override
    public void monitorexit(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("monitorexit"));
    }

    @Override
    public void multianewarray(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("multianewarray"));
    }

    @Override
    public void newMethod(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("new"));
    }

    @Override
    public void newarray(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("newarray"));
    }

    @Override
    public void noMethod(){
    }

    @Override
    public void nop(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("nop"));
    }

    @Override
    public void pop(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("pop"));
    }

    @Override
    public void pop2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("pop2"));
    }

    @Override
    public void putfield(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putfield"));
    }

    @Override
    public void putfield2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putfield2"));
    }

    @Override
    public void putstatic(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putstatic"));
    }

    @Override
    public void putstatic2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putstatic2"));
    }

    @Override
    public void ret(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ret"));
    }

    @Override
    public void returnInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("return"));
    }

    @Override
    public void saload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("saload"));
    }

    @Override
    public void sastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("sastore"));
    }

    @Override
    public void sipush(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("sipush"));
    }

    @Override
    public void swap(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("swap"));
    }

    @Override
    public void tableswitch(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("tableswitch"));
    }
}