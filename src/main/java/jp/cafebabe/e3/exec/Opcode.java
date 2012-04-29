package jp.cafebabe.e3.exec;

/**
 * This class has opcode, opcode name, and corresponding method
 * name for collecting executed opcode.
 *
 * @author Haruaki Tamada
 */
public final class Opcode{
    private int opcode;
    private String methodName;
    private String name;

    /**
     * Constructs object with given arguments.
     * @param opcode Opcode
     * @param name opcode name
     * @param methodName corresponding method name for calling when this opcode is executed.
     */
    public Opcode(final int opcode, final String name, final String methodName){
        this.opcode = opcode;
        this.name = name;
        this.methodName = methodName;
    }

    /**
     * returns the opcode name.
     * @return opcode name
     */
    public String getName(){
        return name;
    }

    /**
     * returns corresponding method name.
     * @return corresponding method name
     */
    public String getMethodName(){
        return methodName;
    }

    /**
     * returns opcode.
     * @return opcode
     */
    public int getOpcode(){
        return opcode;
    }
}