package jp.cafebabe.e3.exec;

import java.io.Serializable;

public class OpcodeFrequency implements Serializable{
    private int opcode;
    private String name;
    private int freq;

    OpcodeFrequency(int opcode, String name, int freq){
        this.opcode = opcode;
        this.name = name;
        this.freq = freq;
    }

    OpcodeFrequency(int opcode, String name){
        this(opcode, name, 0);
    }

    void increment(){
        freq++;
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