package jp.cafebabe.e3.exec;

/**
 * Collecting opcodes sequence per each method.
 * @author Haruaki Tamada
 */
public class MethodEntropyCounter extends DefaultEntropyCounter{
    private static final long serialVersionUID = -8268580315414707344L;

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