package jp.cafebabe.e3.exec.result;

import java.io.IOException;

import jp.cafebabe.e3.exec.KolmogorovCalculator;

public class KolmogorovComplexity{
    private KolmogorovCalculator calculator;
    private byte[] data;
    private int compressedLength;
    private double rate = -1;

    KolmogorovComplexity(KolmogorovCalculator calculator, byte[] data){
        this.calculator = calculator;
        this.data = data;
    }

    /**
     * returns the value of kolmogorov complexity with certain compress algorithm.
     * @param data is used for argument of {@link
     * KolmogorovCalculator#compress
     * <code>KolmogorovCalculator.compress</code>} method.
     * @return calculated kolmogorov complexity.
     */
    public double getComplexity(){
        if(rate < 0){
            try{
                byte[] compressed = calculator.compress(data);
                compressedLength = compressed.length;
                rate = (double)compressed.length / data.length;
            } catch(IOException e){
                rate = -1;
            }
        }
        return rate;
    }

    public String toString(){
        double complexity = getComplexity();
        return String.format(
            "%s,%d,%d,%f", calculator.getName(), compressedLength,
            data.length, complexity
        );
    }
}