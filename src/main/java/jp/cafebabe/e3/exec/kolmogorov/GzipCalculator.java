package jp.cafebabe.e3.exec.kolmogorov;

import org.apache.commons.compress.compressors.CompressorStreamFactory;

/**
 * This class calculate kolmogorov complexity by gzip with
 * Apache commons compress.
 *
 * @see http://commons.apache.org/compress/
 * @author Haruaki Tamada
 */
public class GzipCalculator extends CompressorCalculator{
    public GzipCalculator(){
        super(CompressorStreamFactory.GZIP);
    }
}
