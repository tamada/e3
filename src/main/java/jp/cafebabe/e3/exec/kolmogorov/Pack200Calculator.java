package jp.cafebabe.e3.exec.kolmogorov;

import org.apache.commons.compress.compressors.CompressorStreamFactory;

/**
 * This class calculate kolmogorov complexity by pack200 with
 * Apache commons compress.
 *
 * @see http://commons.apache.org/compress/
 * @author Haruaki Tamada
 */
public class Pack200Calculator extends CompressorCalculator{
    public Pack200Calculator(){
        super(CompressorStreamFactory.PACK200);
    }
}
