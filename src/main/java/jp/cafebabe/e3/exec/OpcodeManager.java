package jp.cafebabe.e3.exec;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;

public class OpcodeManager{
    private static final OpcodeManager instance;
    private Map<String, Opcode> opcodes;

    private OpcodeManager(Map<String, Opcode> opcodes){
        this.opcodes = opcodes;
    }

    public String getMethodName(String opcodeName){
        Opcode opcode = opcodes.get(opcodeName);
        String name = null;
        if(opcode != null){
            name = opcode.getMethodName();
        }
        return name;
    }

    public String getMethodName(int opcode){
        for(Opcode code: opcodes.values()){
            if(code.getOpcode() == opcode){
                return code.getMethodName();
            }
        }
        return null;
    }

    public String getName(int opcode){
        for(Opcode code: opcodes.values()){
            if(code.getOpcode() == opcode){
                return code.getName();
            }
        }
        return null;
    }

    public int getOpcode(String opcodeName){
        Opcode opcode = opcodes.get(opcodeName);
        int id = -1;
        if(opcode != null){
            id = opcode.getOpcode();
        }
        return id;
    }

    public static final OpcodeManager getInstance(){
        return instance;
    }

    static{
        BufferedReader in = null;
        try{
            URL url = OpcodeManager.class.getResource("/resources/bytecode.def");
            String line;
            Map<String, Opcode> opcodeMap = new HashMap<String, Opcode>();

            in = new BufferedReader(new InputStreamReader(url.openStream()));
            while((line = in.readLine()) != null){
                String[] data = line.split(",");
                opcodeMap.put(data[1], new Opcode(Integer.parseInt(data[0]), data[1], data[2]));
            }
            instance = new OpcodeManager(opcodeMap);
        } catch(IOException e){
            throw new InternalError(e.getMessage());
        } finally{
            if(in != null){
                try{
                    in.close();
                } catch(IOException e){
                    throw new InternalError(e.getMessage());
                }
            }
        }
    }
}