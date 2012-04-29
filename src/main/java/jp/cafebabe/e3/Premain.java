package jp.cafebabe.e3;

import java.lang.instrument.Instrumentation;

/**
 * Premain class for Java agent.
 * @author Haruaki Tamada
 */
public final class Premain{
    Premain(final String agentArgs, final Instrumentation inst){
        inst.addTransformer(new OpcodeExtractionTransformer());
    }

    public static void premain(final String agentArgs,
                               final Instrumentation inst){
        new Premain(agentArgs, inst);
    }
}