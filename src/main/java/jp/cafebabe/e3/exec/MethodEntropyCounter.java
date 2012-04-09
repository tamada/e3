package jp.cafebabe.e3.exec;

public class MethodEntropyCounter extends AbstractEntropyCounter{
    private String className;
    private String methodName;

    public MethodEntropyCounter(String className, String methodName){
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName(){
        return className;
    }

    public String getMethodName(){
        return methodName;
    }
}