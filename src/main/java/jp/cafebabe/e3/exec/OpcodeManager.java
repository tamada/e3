package jp.cafebabe.e3.exec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class manage the relationship of instruction and method which
 * is called for executing corresponding instruction.
 * Relationships are build from reading configuration file.
 * </p><p>
 * The configuration file is read in loading phase by static
 * initializer of this class.
 * </p>
 * @author Haruaki Tamada
 */
public final class OpcodeManager{
    private static final OpcodeManager INSTANCE;
    private final Map<String, Opcode> opcodes;

    /**
     * private constructor for singleton pattern.
     * @param opcodes opcodes map from configuration file.
     */
    private OpcodeManager(final Map<String, Opcode> opcodes){
        this.opcodes = opcodes;
    }

    /**
     * returns the number of opcodes.
     * @return opcode count
     */
    public int getSize(){
        return opcodes.size();
    }

    /**
     * returns corresponding method name of given opcode name.  This
     * method returns null, when opcode is not found, and given opcode
     * name is null.
     * @param opcodeName opcode name
     * @return corresponding method name.
     */
    public String getMethodName(final String opcodeName){
        Opcode opcode = opcodes.get(opcodeName);
        String name = null;
        if(opcode != null){
            name = opcode.getMethodName();
        }
        return name;
    }

    /**
     * returns corresponding method name of given opcode.
     * If given opcode is invalid, this method returns null.
     * @param opcode opcode
     * @return corresponding method name.
     */
    public String getMethodName(final int opcode){
        for(Opcode code: opcodes.values()){
            if(code.getOpcode() == opcode){
                return code.getMethodName();
            }
        }
        return null;
    }

    /**
     * returns opcode name of given opcode.
     * If given opcode is invalid, this method returns null.
     * @param opcode opcode
     * @return corresponding opcode name
     */
    public String getName(final int opcode){
        for(Opcode code: opcodes.values()){
            if(code.getOpcode() == opcode){
                return code.getName();
            }
        }
        return null;
    }

    /**
     * returns opcode of given opcode name.  If given opcode name is
     * null, or corresponding opcode is not found, this method returns
     * null.
     * @param opcodeName opcode name
     * @return corresponding opcode
     */
    public int getOpcode(final String opcodeName){
        Opcode opcode = opcodes.get(opcodeName);
        int id = -1;
        if(opcode != null){
            id = opcode.getOpcode();
        }
        return id;
    }

    /**
     * returns singleton object.
     */
    public static OpcodeManager getInstance(){
        return INSTANCE;
    }

    static{
        Logger logger = Logger.getLogger(OpcodeManager.class.getName());
        BufferedReader in = null;
        Map<String, Opcode> opcodeMap = new HashMap<String, Opcode>();
        INSTANCE = new OpcodeManager(opcodeMap);
        try{
            URL url = OpcodeManager.class.getResource(
                "/resources/bytecode.def"
            );
            String line;

            in = new BufferedReader(
                new InputStreamReader(url.openStream(), "utf-8")
            );
            while((line = in.readLine()) != null){
                String[] data = line.split(",");
                opcodeMap.put(
                    data[1],
                    new Opcode(Integer.parseInt(data[0]), data[1], data[2])
                );
            }
        }
        catch(IOException e){
            logger.log(Level.WARNING, e.getMessage(), e);
        }
        finally{
            if(in != null){
                try{
                    in.close();
                }
                catch(IOException e){
                    logger.log(Level.WARNING, e.getMessage(), e);
                }
            }
        }
    }
}