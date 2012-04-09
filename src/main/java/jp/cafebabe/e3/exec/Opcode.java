package jp.cafebabe.e3.exec;

public class Opcode{
    private int opcode;
    private String methodName;
    private String name;

    public Opcode(int opcode, String name, String methodName){
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