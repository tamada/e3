package jp.cafebabe.e3.exec;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.cafebabe.e3.MultipleIterator;
import jp.cafebabe.e3.exec.result.OpcodeFrequency;

public class MultipleEntropyCounter extends EmptyEntropyCounter implements Iterable<EntropyCounter>, Serializable{
    private static final long serialVersionUID = 1217257625796127665L;

    private List<EntropyCounter> counters = new ArrayList<EntropyCounter>();
    private int totalSize = -1;
    private double entropy = -1;

    public MultipleEntropyCounter(EntropyCounter counter){
        this(new EntropyCounter[] { counter, });
    }

    public MultipleEntropyCounter(EntropyCounter[] counterArray){
        for(EntropyCounter ec: counterArray){
            counters.add(ec);
        }
    }

    public Iterator<EntropyCounter> iterator(){
        return counters.iterator();
    }

    @Override
    public Iterator<Integer> opcodes(){
        List<Iterator<Integer>> iterators = new ArrayList<Iterator<Integer>>();
        for(EntropyCounter ec: counters){
            iterators.add(ec.opcodes());
        }
        return new MultipleIterator<Integer>(iterators);
    }

    @Override
    public Iterator<OpcodeFrequency> frequencies(){
        List<Iterator<OpcodeFrequency>> iterators = new ArrayList<Iterator<OpcodeFrequency>>();
        for(EntropyCounter ec: counters){
            iterators.add(ec.frequencies());
        }
        return new MultipleIterator<OpcodeFrequency>(iterators);
    }

    @Override
    public int getSize(){
        if(totalSize < 0){
            int size = 0;
            for(EntropyCounter ec: counters){
                size += ec.getSize();
            }
            this.totalSize = size;
        }
        return totalSize;
    }

    public final double getEntropy(){
        if(entropy < 0){
            entropy = calculateEntropy();
        }
        return entropy;
    }

    private double calculateEntropy(){
        // 出現した命令を基にエントロピーを計算する．
        double entropy = 0d;
        double log2 = Math.log(2);
        int total = getSize();
    
        for(Iterator<OpcodeFrequency> i = frequencies(); i.hasNext(); ){
            OpcodeFrequency freq = i.next();
            double probability = (double)freq.getFrequency() / total;
            entropy += probability * (Math.log(probability) / log2);
        }
        entropy = -1 * entropy;
        return entropy;
    }
}
