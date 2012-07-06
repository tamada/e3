package jp.cafebabe.e3.exec.kolmogorov;

import org.apache.commons.compress.compressors.CompressorStreamFactory;

/**
 * This class calculate kolmogorov complexity by XZ with
 * Apache commons compress.
 *
 * @see http://commons.apache.org/compress/
 * @author Haruaki Tamada
 */
public class XzCalculator extends CompressorCalculator{
    public XzCalculator(){
        super(CompressorStreamFactory.XZ);
    }
}
