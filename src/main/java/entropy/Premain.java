package entropy;

import java.lang.instrument.Instrumentation;
import java.util.*;
import java.security.ProtectionDomain;

public class Premain{
    public Premain(String agentArgs, Instrumentation inst){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                EntropyCounterManager.getInstance().summarize();
            }
        });

        inst.addTransformer(new OpcodeExtractionTransformer());
    }

    public static void premain(String agentArgs, Instrumentation inst){
        new Premain(agentArgs, inst);
    }
}