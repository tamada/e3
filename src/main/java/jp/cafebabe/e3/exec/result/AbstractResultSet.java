package jp.cafebabe.e3.exec.result;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;

import jp.cafebabe.e3.exec.Entropy;

public abstract class AbstractResultSet implements Serializable, ResultSet{
    private static final long serialVersionUID = 8890087986391605380L;
    static final String ALL_METHODS = "<all-methods>";
    static final String ALL_CLASSES = "<all-classes>";
    static final String UNKNOWN_THREAD = "<unknown-thread>";

    public AbstractResultSet(){
    }

    @Override
    public abstract Iterator<Entropy> entropies();

    @Override
    public abstract Iterator<OpcodeFrequency> frequencies();

    @Override
    public abstract Iterator<ResultOpcode> executionTraces();

    @Override
    public final void print(PrintWriter out){
        printExecutionTraces(out);
        printFrequencies(out);
        printEntropies(out);
        printKolmogorovComplexities(out);

        out.flush();
    }

    @Override
    public void printExecutionTraces(PrintWriter out){
        printExecutionTraces(out, true);
    }

    @Override
    public void printFrequencies(PrintWriter out){
        printFrequencyOfTrace(out, true);
    }

    @Override
    public void printEntropies(PrintWriter out){
        printEntropies(out, true);
    }

    @Override
    public void printKolmogorovComplexities(PrintWriter out){
        printKolmogorovComplexities(out, true);
    }

    public void printExecutionTraces(PrintWriter out, boolean showHeader){
        if(showHeader){
            out.println(
                "######## execution trace (thread,class,method,opcode,name,line) ##########"
            );
        }
        for(Iterator<ResultOpcode> i = executionTraces(); i.hasNext(); ){
            out.println(i.next());
        }
    }

    public void printFrequencyOfTrace(PrintWriter out, boolean showHeader){
        if(showHeader){
            out.println(
                "################ frequency of trace (opcode,name,count) ##################"
            );
        }
        for(Iterator<OpcodeFrequency> i = frequencies(); i.hasNext(); ){
            out.println(i.next());
        }
    }

    public void printEntropies(PrintWriter out, boolean showHeader){
        if(showHeader){
            out.println(
                "##################### entropy (class,method,entropy) #####################"
            );
        }
        for(Iterator<Entropy> i = entropies(); i.hasNext(); ){
            out.println(i.next());
        }
    }

    public void printKolmogorovComplexities(PrintWriter out, boolean showHeader){
        if(showHeader){
            out.println(
                "########## kolmogorov complexity (algorithm,after,before,rate) ###########"
            );
        }
        for(Iterator<KolmogorovComplexity> i = kolmogorovComplexities(); i.hasNext(); ){
            KolmogorovComplexity complexity = i.next();
            out.println(complexity);
        }
    }
}