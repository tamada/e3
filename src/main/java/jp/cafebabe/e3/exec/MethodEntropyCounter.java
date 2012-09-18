package jp.cafebabe.e3.exec;

/**
 * Collecting opcodes sequence per each method.
 * @author Haruaki Tamada
 */
public class MethodEntropyCounter extends DefaultEntropyCounter{
    private static final long serialVersionUID = -8268580315414707344L;

    private final String className;
    private final String methodName;
    private final String threadName;

    public MethodEntropyCounter(final String className,
                                final String methodName,
                                final String threadName){
        this.className = className;
        this.methodName = methodName;
        this.threadName = threadName;
    }

    public final String getClassName(){
        return className;
    }

    public final String getMethodName(){
        return methodName;
    }

    public final String getThreadName(){
        return threadName;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getThreadName()).append(",").append(getClassName());
        sb.append(",").append(getMethodName());
        return new String(sb);
    }
}