package jp.cafebabe.e3.exec.kolmogorov;

import org.apache.commons.compress.compressors.CompressorStreamFactory;

/**
 * This class calculate kolmogorov complexity by bzip2 with
 * Apache commons compress.
 *
 * @see http://commons.apache.org/compress/
 * @author Haruaki Tamada
 */
public class Bzip2Calculator extends CompressorCalculator{
    public Bzip2Calculator(){
        super(CompressorStreamFactory.BZIP2);
    }
}
