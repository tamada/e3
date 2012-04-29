package jp.cafebabe.e3;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

/**
 * Premain class for Java agent.
 * @author Haruaki Tamada
 */
public final class Premain{
    private TransformFilter filter;

    Premain(final String agentArgs, final Instrumentation inst) throws IOException{
        parseArguments(agentArgs);
        inst.addTransformer(new OpcodeExtractionTransformer(filter));
    }

    private void parseArguments(String args) throws IOException{
        if(args == null || args.trim().length() == 0){
            return;
        }
        String[] arguments = args.split(",");
        for(String arg: arguments){
            if(arg.startsWith("filter:")){
                filter = new CsvTransformFilter(arg.substring("filter:".length()));
            }
        }
    }

    public static void premain(final String agentArgs,
                               final Instrumentation inst) throws IOException{
        new Premain(agentArgs, inst);
    }
}