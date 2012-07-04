package jp.cafebabe.e3.exec.result;

import java.io.PrintWriter;
import java.util.Iterator;

import jp.cafebabe.e3.exec.Entropy;

/**
 * This class represents the result of execution.
 * 
 * @author Haruaki Tamada
 */
public interface ResultSet{
    Iterator<ResultOpcode> executionTraces();

    Iterator<Entropy> entropies();

    Iterator<OpcodeFrequency> frequencies();

    Iterator<KolmogorovComplexity> kolmogorovComplexities();

    void print(PrintWriter out);

    void printExecutionTraces(PrintWriter out);

    void printFrequencies(PrintWriter out);

    void printEntropies(PrintWriter out);

    void printKolmogorovComplexities(PrintWriter out);
}
