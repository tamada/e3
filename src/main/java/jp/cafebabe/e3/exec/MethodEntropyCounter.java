package jp.cafebabe.e3.exec;

/**
 * Collecting opcodes sequence per each method.
 * @author Haruaki Tamada
 */
public class MethodEntropyCounter extends AbstractEntropyCounter{
    private final String className;
    private final String methodName;

    public MethodEntropyCounter(final String className,
                                final String methodName){
        this.className = className;
        this.methodName = methodName;
    }

    public final String getClassName(){
        return className;
    }

    public final String getMethodName(){
        return methodName;
    }
}