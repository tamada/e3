package jp.cafebabe.e3;

import java.lang.instrument.Instrumentation;

public final class Premain{
    Premain(final String agentArgs, final Instrumentation inst){
        inst.addTransformer(new OpcodeExtractionTransformer());
    }

    public static void premain(final String agentArgs,
                               final Instrumentation inst){
        new Premain(agentArgs, inst);
    }
}