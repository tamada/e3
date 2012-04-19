package jp.cafebabe.e3.exec;

/**
 *
 * @author Haruaki Tamada
 */
public final class Opcode{
    private int opcode;
    private String methodName;
    private String name;

    public Opcode(final int opcode, final String name, final String methodName){
        this.opcode = opcode;
        this.name = name;
        this.methodName = methodName;
    }

    public String getName(){
        return name;
    }

    public String getMethodName(){
        return methodName;
    }

    public int getOpcode(){
        return opcode;
    }
}