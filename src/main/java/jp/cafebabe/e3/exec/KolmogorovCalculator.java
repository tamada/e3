package jp.cafebabe.e3.exec;

import java.io.IOException;

public interface KolmogorovCalculator{
    public String getSummary(byte[] data);

    public byte[] compress(byte[] data) throws IOException;

    public String getName();
}