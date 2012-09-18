package jp.cafebabe.e3.exec;

public interface Entropy{
    String getClassName();

    String getMethodName();

    String getThreadName();

    double getEntropy();
}
