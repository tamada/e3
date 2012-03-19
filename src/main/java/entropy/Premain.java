package entropy;

import java.lang.instrument.Instrumentation;

public class Premain{
    public Premain(String agentArgs, Instrumentation inst){
        inst.addTransformer(new OpcodeExtractionTransformer());
    }

    public static void premain(String agentArgs, Instrumentation inst){
        new Premain(agentArgs, inst);
    }
}