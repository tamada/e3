package jp.cafebabe.e3.exec.result;

import java.io.Serializable;

public class OpcodeFrequency implements Serializable{
    private static final long serialVersionUID = -1673197729533037597L;

    private int opcode;
    private String name;
    private int freq;

    public OpcodeFrequency(int opcode, String name, int freq){
        this.opcode = opcode;
        this.name = name;
        this.freq = freq;
    }

    public OpcodeFrequency(int opcode, String name){
        this(opcode, name, 0);
    }

    public void increment(){
        freq++;
    }

    public void increment(int operand){
        freq += operand;
    }

    public int getOpcode(){
        return opcode;
    }

    public String getName(){
        return name;
    }

    public int getFrequency(){
        return freq;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(opcode).append(",").append(name).append(",").append(freq);
        return new String(sb);
    }
}