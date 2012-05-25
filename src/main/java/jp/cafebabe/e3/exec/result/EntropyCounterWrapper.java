package jp.cafebabe.e3.exec.result;

import jp.cafebabe.e3.exec.Entropy;
import jp.cafebabe.e3.exec.EntropyCounter;
import jp.cafebabe.e3.exec.MethodEntropyCounter;

public class EntropyCounterWrapper implements Entropy{
    static final String UNKNOWN_NAME = "<unknown>";
    private EntropyCounter counter;
    private String className;
    private String methodName;

    public EntropyCounterWrapper(EntropyCounter counter){
        if(counter instanceof MethodEntropyCounter){
            MethodEntropyCounter mec = (MethodEntropyCounter)counter;
            className = mec.getClassName();
            methodName = mec.getMethodName();
        }
        else{
            className = UNKNOWN_NAME;
            methodName = UNKNOWN_NAME;
        }
        this.counter = counter;
    }

    public EntropyCounterWrapper(EntropyCounter counter, String className, String methodName){
        this.counter = counter;
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public String getClassName(){
        return className;
    }

    @Override
    public String getMethodName(){
        return methodName;
    }

    @Override
    public double getEntropy(){
        return counter.getEntropy();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getClassName()).append(",").append(getMethodName());
        sb.append(",").append(getEntropy());

        return new String(sb);
    }
}
