package jp.cafebabe.e3.exec;

import java.io.Serializable;

public class ResultOpcode implements Serializable{
    private String className;
    private String methodName;
    private int opcode;
    private String name;

    public ResultOpcode(String className, String methodName, int opcode, String name){
        this.className = className;
        this.methodName = methodName;
        this.opcode = opcode;
        this.name = name;
    }

    public String getClassName(){
        return className;
    }

    public String getMethodName(){
        return methodName;
    }

    public int getOpcode(){
        return opcode;
    }

    public String getOpcodeName(){
        return name;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClassName()).append(",").append(getMethodName()).append(",");
        sb.append(getOpcode()).append(",").append(getOpcodeName());
        return new String(sb);
    }
}