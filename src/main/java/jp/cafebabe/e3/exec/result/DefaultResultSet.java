package jp.cafebabe.e3.exec.result;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import jp.cafebabe.e3.exec.Entropy;
import jp.cafebabe.e3.exec.EntropyCounter;
import jp.cafebabe.e3.exec.KolmogorovCalculator;
import jp.cafebabe.e3.exec.MethodEntropyCounter;
import jp.cafebabe.e3.exec.MultipleEntropyCounter;
import jp.cafebabe.e3.exec.OpcodeManager;

public class DefaultResultSet extends AbstractResultSet implements Serializable{
    private static final long serialVersionUID = 8890087986391605380L;

    MultipleEntropyCounter counter;
    private byte[] data;

    public DefaultResultSet(List<EntropyCounter> list){
        counter = new MultipleEntropyCounter(list.toArray(new EntropyCounter[list.size()]));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for(Iterator<Integer> i = counter.opcodes(); i.hasNext(); ){
            Integer opcode = i.next();
            out.write(opcode);
        }

        this.data = out.toByteArray();
    }

    @Override
    public Iterator<Entropy> entropies(){
        List<Entropy> list = new ArrayList<Entropy>();
        for(EntropyCounter ec: counter){
            list.add(new EntropyCounterWrapper(ec));
        }
        return list.iterator();
    }

    @Override
    public Iterator<OpcodeFrequency> frequencies(){
        return counter.frequencies();
    }

    @Override
    public Iterator<ResultOpcode> executionTraces(){
        List<ResultOpcode> list = new ArrayList<ResultOpcode>();
        OpcodeManager manager = OpcodeManager.getInstance();
        for(EntropyCounter ec: counter){
            MethodEntropyCounter mec = (MethodEntropyCounter)ec;
            String className = mec.getClassName();
            String methodName = mec.getMethodName();
            int count = 0;
            int line = mec.getLine(count);
            for(Iterator<Integer> i = mec.opcodes(); i.hasNext(); ){
                Integer opcode = i.next();
                String name = manager.getName(opcode);
                list.add(new ResultOpcode(className, methodName, opcode, name, line));

                count++;
                int newLine = mec.getLine(count);
                if(newLine != -1){
                    line = newLine;
                }
            }
        }
        return list.iterator();
    }

    public Iterator<KolmogorovComplexity> kolmogorovComplexities(){
        return new Iterator<KolmogorovComplexity>(){
            private Iterator<KolmogorovCalculator> iterator =
                ServiceLoader.load(KolmogorovCalculator.class).iterator();

            @Override
            public boolean hasNext(){
                return iterator.hasNext();
            }

            @Override
            public KolmogorovComplexity next(){
                KolmogorovCalculator calculator = iterator.next();

                return new KolmogorovComplexity(calculator, data);
            }

            @Override
            public void remove(){
            }
        };
    }

    public void printEntropies(PrintWriter out, boolean showHeader){
        super.printEntropies(out, showHeader);
        out.println(new EntropyCounterWrapper(counter, WHOLE_SEQUENCE, ENTIRE_LABEL));
    }
}