package jp.cafebabe.e3.exec;

import java.io.IOException;

/**
 * 
 */
public interface KolmogorovCalculator{
    /**
     * returns summarize string for output.
     * @see EntropyCounterManager#summarize
     * @param data is used for argument of {@link compress
     * <code>compress</code>} method.
     */
    String getSummary(byte[] data);

    /**
     * perform compress.
     * @param data is original data for compression
     * @return compressed data
     */
    byte[] compress(byte[] data) throws IOException;

    /**
     * returns the algorithm name of this implemantation class.
     * @return compress algorithm name
     */
    String getName();

    /**
     * Returns true if the class implemented this interface is
     * available to execute.  If class is not available since the
     * required libraries are missing, this method returns false.
     * @return true if the object can perform.
     */
    boolean isAvailable();
}