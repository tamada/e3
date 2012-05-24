package jp.cafebabe.e3.exec.result;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import jp.cafebabe.e3.exec.EntropyCounter;
import jp.cafebabe.e3.exec.KolmogorovCalculator;
import jp.cafebabe.e3.exec.MethodEntropyCounter;
import jp.cafebabe.e3.exec.MultipleEntropyCounter;
import jp.cafebabe.e3.exec.OpcodeManager;

public class ResultSet implements Serializable{
    private static final long serialVersionUID = 8890087986391605380L;

    private MultipleEntropyCounter counter;
    private byte[] data;

    public ResultSet(List<EntropyCounter> list){
        counter = new MultipleEntropyCounter(list.toArray(new EntropyCounter[list.size()]));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for(Iterator<Integer> i = counter.opcodes(); i.hasNext(); ){
            Integer opcode = i.next();
            out.write(opcode);
        }

        this.data = out.toByteArray();
    }

    public void print(PrintWriter out){
        OpcodeManager manager = OpcodeManager.getInstance();
        out.println(
            "########### execution trace (class,method,opcode,name,line) #############"
        );
        for(EntropyCounter ec: counter){
            MethodEntropyCounter mec = (MethodEntropyCounter)ec;
            String className = mec.getClassName();
            String methodName = mec.getMethodName();
            int count = 0;
            int line = mec.getLine(count);
            for(Iterator<Integer> i = mec.opcodes(); i.hasNext(); ){
                Integer opcode = i.next();
                String name = manager.getName(opcode);
                out.println(new ResultOpcode(className, methodName, opcode, name, line));

                count++;
                int newLine = mec.getLine(count);
                if(newLine != -1){
                    line = newLine;
                }
            }
        }
        out.println(
            "################ frequency of trace (opcode,name,count) #################"
        );
        for(Iterator<OpcodeFrequency> i = counter.frequencies(); i.hasNext(); ){
            OpcodeFrequency freq = i.next();
            out.println(freq);
        }
        out.println(
            "##################### entropy (class,method,entropy) #####################"
        );
        for(EntropyCounter ec: counter){
            MethodEntropyCounter mec = (MethodEntropyCounter)ec;
            out.printf("%s,%s,%g%n", mec.getClassName(), mec.getMethodName(), mec.getEntropy());
        }
        out.printf("<whole>,<entire>,%g%n", getEntropy());
        out.println(
            "########## kolmogorov complexity (algorithm,after,before,rate) ##########"
        );
        for(Iterator<KolmogorovComplexity> i = kolmogorovComplexities(); i.hasNext(); ){
            KolmogorovComplexity complexity = i.next();
            out.println(complexity);
        }
        out.flush();
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

    public int getExecutionTraceLength(){
        return counter.getSize();
    }

    public synchronized double getEntropy(){
        return counter.getEntropy();
    }
}