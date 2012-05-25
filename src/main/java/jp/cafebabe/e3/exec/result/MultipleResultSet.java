package jp.cafebabe.e3.exec.result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.cafebabe.e3.MultipleIterator;
import jp.cafebabe.e3.exec.Entropy;
import jp.cafebabe.e3.exec.EntropyCounter;
import jp.cafebabe.e3.exec.MultipleEntropyCounter;

public class MultipleResultSet extends AbstractResultSet{
    private static final long serialVersionUID = -6581879767335889153L;

    private List<ResultSet> rslist = new ArrayList<ResultSet>();

    public MultipleResultSet(){
    }

    public MultipleResultSet(ResultSet[] rsArray){
        for(ResultSet rs: rsArray){
            add(rs);
        }
    }

    public void add(ResultSet rs){
        if(rs == null){
            throw new NullPointerException();
        }
        rslist.add(rs);
    }

    @Override
    public Iterator<ResultOpcode> executionTraces(){
        List<Iterator<ResultOpcode>> list = new ArrayList<Iterator<ResultOpcode>>();
        for(ResultSet rs: rslist){
            list.add(rs.executionTraces());
        }
        return new MultipleIterator<ResultOpcode>(list);
    }

    @Override
    public Iterator<Entropy> entropies(){
        List<Entropy> list = new ArrayList<Entropy>();
        List<EntropyCounter> eclist = new ArrayList<EntropyCounter>();
        for(ResultSet rs: rslist){
            String className = null;
            boolean equals = true;
            for(Iterator<Entropy> i = rs.entropies(); i.hasNext(); ){
                Entropy e = i.next();
                list.add(e);
                if(className == null){
                    className = e.getClassName();
                }
                if(!className.equals(e.getClassName())){
                    equals = false;
                }
            }
            if(equals){
                list.add(new EntropyCounterWrapper(((DefaultResultSet)rs).counter, className, ENTIRE_LABEL));
            }
            eclist.add(((DefaultResultSet)rs).counter);
        }
        list.add(new EntropyCounterWrapper(
            new MultipleEntropyCounter(eclist.toArray(new EntropyCounter[eclist.size()])),
            WHOLE_SEQUENCE, ENTIRE_LABEL
        ));
        return list.iterator();
    }

    @Override
    public Iterator<OpcodeFrequency> frequencies(){
        Map<Integer, OpcodeFrequency> map = new TreeMap<Integer, OpcodeFrequency>();
        for(ResultSet rs: rslist){
            for(Iterator<OpcodeFrequency> i = rs.frequencies(); i.hasNext(); ){
                OpcodeFrequency freq = i.next();
                OpcodeFrequency orig = map.get(freq.getOpcode());
                if(orig == null){
                    map.put(freq.getOpcode(), freq);
                }
                else{
                    orig.increment(freq.getFrequency());
                }
            }
        }
        return map.values().iterator();
    }

    @Override
    public Iterator<KolmogorovComplexity> kolmogorovComplexities(){
        return new ArrayList<KolmogorovComplexity>().iterator();
    }
}
