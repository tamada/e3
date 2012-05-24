package jp.cafebabe.e3.exec.result;

import java.io.Serializable;

public class ResultOpcode implements Serializable{
    private static final long serialVersionUID = -2706797011570020246L;

    private String className;
    private String methodName;
    private int opcode;
    private String name;
    private int line = -1;

    public ResultOpcode(String className, String methodName, int opcode, String name){
        this(className, methodName, opcode, name, -1);
    }

    public ResultOpcode(String className, String methodName, int opcode, String name, int line){
        this.className = className;
        this.methodName = methodName;
        this.opcode = opcode;
        this.name = name;
        this.line = line;
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

    public int getLineNumber(){
        return line;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClassName()).append(",").append(getMethodName()).append(",");
        sb.append(getOpcode()).append(",").append(getOpcodeName()).append(",").append(line);
        return new String(sb);
    }
}