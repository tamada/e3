package jp.cafebabe.e3.exec;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ServiceLoader;

public class ResultSet implements Serializable{
    private List<ResultOpcode> opcodeList = new ArrayList<ResultOpcode>();
    private Map<Integer, OpcodeFrequency> opcodeFreq = new TreeMap<Integer, OpcodeFrequency>();
    private byte[] data;
    private double entropy = -1d;

    ResultSet(List<EntropyCounter> list){
        OpcodeManager manager = OpcodeManager.getInstance();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for(EntropyCounter mec: list){
            String className = ((MethodEntropyCounter)mec).getClassName();
            String methodName = ((MethodEntropyCounter)mec).getMethodName();
            for(Integer opcode: mec){
                String name = manager.getName(opcode);
                opcodeList.add(new ResultOpcode(className, methodName, opcode, name));

                OpcodeFrequency freq = opcodeFreq.get(opcode);
                if(freq == null){
                    freq = new OpcodeFrequency(opcode, name);
                    opcodeFreq.put(opcode, freq);
                }
                freq.increment();
                out.write(opcode);
            }
        }

        this.data = out.toByteArray();
    }

    public void print(PrintWriter out){
        out.println(
            "############## execution trace (class,method,opcode,name) ###############"
        );
        for(ResultOpcode ro: opcodeList){
            out.println(ro);
        }
        out.println(
            "################ frequency of trace (opcode,name,count) #################"
        );
        for(OpcodeFrequency freq: opcodeFreq.values()){
            out.println(freq);
        }
        out.println(
            "################################ entropy ################################"
        );
        out.println(getEntropy());
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
        return opcodeList.size();
    }

    public Iterator<ResultOpcode> executionTrace(){
        return opcodeList.iterator();
    }

    public int getFrequencySize(){
        return opcodeFreq.size();
    }

    public Iterator<OpcodeFrequency> frequencies(){
        return opcodeFreq.values().iterator();
    }

    public synchronized double getEntropy(){
        if(entropy < 0){
            entropy = calculateEntropy();
        }
        return entropy;
    }

    private double calculateEntropy(){
        // 出現した命令を基にエントロピーを計算する．
        double entropy = 0d;
        double log2 = Math.log(2);
        int total = getExecutionTraceLength();

        for(OpcodeFrequency freq: opcodeFreq.values()){
            double probability = (double)freq.getFrequency() / total;
            entropy += probability * (Math.log(probability) / log2);
        }
        entropy = -1 * entropy;
        return entropy;
    }
}