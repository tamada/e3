package entropy;

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

    public void aaload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aaload"));
    }

    public void aastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aastore"));
    }

    public void aconstNull(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aconst_null"));
    }

    public void aload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("aload"));
    }

    public void anewarray(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("anewarray"));
    }

    public void areturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("areturn"));
    }

    public void arraylength(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("arraylength"));
    }

    public void astore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("astore"));
    }

    public void athrow(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("athrow"));
    }

    public void baload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("baload"));
    }

    public void bastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("bastore"));
    }

    public void bipush(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("bipush"));
    }

    public void breakpoint(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("breakpoint"));
    }

    public void caload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("caload"));
    }

    public void castore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("castore"));
    }

    public void checkcast(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("checkcast"));
    }

    public void d2f(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("d2f"));
    }

    public void d2i(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("d2i"));
    }

    public void d2l(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("d2l"));
    }

    public void dadd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dadd"));
    }

    public void daload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("daload"));
    }

    public void dastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dastore"));
    }

    public void dcmpg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dcmpg"));
    }

    public void dcmpl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dcmpl"));
    }

    public void dconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dconst_0"));
    }

    public void dconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dconst_1"));
    }

    public void ddiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ddiv"));
    }

    public void dload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dload"));
    }

    public void dmul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dmul"));
    }

    public void dneg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dneg"));
    }

    public void drem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("drem"));
    }

    public void dreturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dreturn"));
    }

    public void dstore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dstore"));
    }

    public void dsub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dsub"));
    }

    public void dup(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup"));
    }

    public void dup2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup2"));
    }

    public void dup2X1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup2_x1"));
    }

    public void dup2X2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup2_x2"));
    }

    public void dupX1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup_x1"));
    }

    public void dupX2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("dup_x2"));
    }

    public void f2d(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("f2d"));
    }

    public void f2i(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("f2i"));
    }

    public void f2l(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("f2l"));
    }

    public void fadd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fadd"));
    }

    public void faload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("faload"));
    }

    public void fastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fastore"));
    }

    public void fcmpg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fcmpg"));
    }

    public void fcmpl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fcmpl"));
    }

    public void fconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fconst_0"));
    }

    public void fconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fconst_1"));
    }

    public void fconst2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fconst_2"));
    }

    public void fdiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fdiv"));
    }

    public void fload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fload"));
    }

    public void fmul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fmul"));
    }

    public void fneg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fneg"));
    }

    public void frem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("frem"));
    }

    public void freturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("freturn"));
    }

    public void fstore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fstore"));
    }

    public void fsub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("fsub"));
    }

    public void getfield(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getfield"));
    }

    public void getfield2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getfield2"));
    }

    public void getstatic(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getstatic"));
    }

    public void getstatic2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("getstatic2"));
    }

    public void gotoInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("goto"));
    }

    public void i2b(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2b"));
    }

    public void i2c(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2c"));
    }

    public void i2d(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2d"));
    }

    public void i2f(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2f"));
    }

    public void i2l(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2l"));
    }

    public void i2s(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("i2s"));
    }

    public void iadd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iadd"));
    }

    public void iaload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iaload"));
    }

    public void iand(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iand"));
    }

    public void iastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iastore"));
    }

    public void iconstM1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_m1"));
    }

    public void iconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_0"));
    }

    public void iconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_1"));
    }

    public void iconst2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_2"));
    }

    public void iconst3(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_3"));
    }

    public void iconst4(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_4"));
    }

    public void iconst5(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iconst_5"));
    }

    public void idiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("idiv"));
    }

    public void ifAcmpeq(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_acmpeq"));
    }

    public void ifAcmpne(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_acmpne"));
    }

    public void ifIcmpeq(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpeq"));
    }

    public void ifIcmpge(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpge"));
    }

    public void ifIcmpgt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpgt"));
    }

    public void ifIcmple(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmple"));
    }

    public void ifIcmplt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmplt"));
    }

    public void ifIcmpne(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("if_icmpne"));
    }

    public void ifeq(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifeq"));
    }

    public void ifge(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifge"));
    }

    public void ifgt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifgt"));
    }

    public void ifle(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifle"));
    }

    public void iflt(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iflt"));
    }

    public void ifne(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifne"));
    }

    public void ifnonnull(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifnonnull"));
    }

    public void ifnull(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ifnull"));
    }

    public void iinc(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iinc"));
    }

    public void iload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iload"));
    }

    public void impdep(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("impdep"));
    }

    public void imul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("imul"));
    }

    public void ineg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ineg"));
    }

    public void instanceofInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("instanceof"));
    }

    public void invokeinterface(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokeinterface"));
    }

    public void invokenonvirtual(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokenonvirtual"));
    }

    public void invokespecial(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokespecial"));
    }

    public void invokestatic(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokestatic"));
    }

    public void invokesuper(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokesuper"));
    }

    public void invokevirtual(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokevirtual"));
    }

    public void invokevirtualobject(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("invokevirtualobject"));
    }

    public void ior(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ior"));
    }

    public void irem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("irem"));
    }

    public void ireturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ireturn"));
    }

    public void ishl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ishl"));
    }

    public void ishr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ishr"));
    }

    public void istore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("istore"));
    }

    public void isub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("isub"));
    }

    public void iushr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("iushr"));
    }

    public void ixor(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ixor"));
    }

    public void jsr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("jsr"));
    }

    public void l2d(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("l2d"));
    }

    public void l2f(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("l2f"));
    }

    public void l2i(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("l2i"));
    }

    public void ladd(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ladd"));
    }

    public void laload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("laload"));
    }

    public void land(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("land"));
    }

    public void lastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lastore"));
    }

    public void lcmp(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lcmp"));
    }

    public void lconst0(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lconst_0"));
    }

    public void lconst1(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lconst_1"));
    }

    public void ldc(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ldc"));
    }

    public void ldc2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ldc2"));
    }

    public void ldiv(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ldiv"));
    }

    public void lload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lload"));
    }

    public void lmul(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lmul"));
    }

    public void lneg(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lneg"));
    }

    public void lookupswitch(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lookupswitch"));
    }

    public void lor(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lor"));
    }

    public void lrem(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lrem"));
    }

    public void lreturn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lreturn"));
    }

    public void lshl(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lshl"));
    }

    public void lshr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lshr"));
    }

    public void lstore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lstore"));
    }

    public void lsub(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lsub"));
    }

    public void lushr(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lushr"));
    }

    public void lxor(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("lxor"));
    }

    public void monitorenter(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("monitorenter"));
    }

    public void monitorexit(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("monitorexit"));
    }

    public void multianewarray(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("multianewarray"));
    }

    public void newMethod(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("new"));
    }

    public void newarray(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("newarray"));
    }

    public void noMethod(){
    }

    public void nop(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("nop"));
    }

    public void pop(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("pop"));
    }

    public void pop2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("pop2"));
    }

    public void putfield(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putfield"));
    }

    public void putfield2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putfield2"));
    }

    public void putstatic(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putstatic"));
    }

    public void putstatic2(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("putstatic2"));
    }

    public void ret(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("ret"));
    }

    public void returnInsn(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("return"));
    }

    public void saload(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("saload"));
    }

    public void sastore(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("sastore"));
    }

    public void sipush(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("sipush"));
    }

    public void swap(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("swap"));
    }

    public void tableswitch(){
        opcodes.add(OpcodeManager.getInstance().getOpcode("tableswitch"));
    }
}