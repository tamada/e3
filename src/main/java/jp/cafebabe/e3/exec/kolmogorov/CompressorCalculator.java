package jp.cafebabe.e3.exec.kolmogorov;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/**
 * This class calculate kolmogorov complexity by Apache commons compress.
 *
 * @see http://commons.apache.org/compress/
 * @author Haruaki Tamada
 */
public abstract class CompressorCalculator extends AbstractCalculator{
    private static final Set<String> TYPE_SET = new HashSet<String>();
    static{
        TYPE_SET.add(CompressorStreamFactory.BZIP2);
        TYPE_SET.add(CompressorStreamFactory.GZIP);
        TYPE_SET.add(CompressorStreamFactory.PACK200);
        TYPE_SET.add(CompressorStreamFactory.XZ);
    }

    private String type;

    /**
     * Type allows the static fields of CompressorStreamFactory.
     */
    protected CompressorCalculator(String type){
        if(!TYPE_SET.contains(type)){
            throw new IllegalArgumentException(type);
        }
        this.type = type;
    }

    public String getName(){
        return type;
    }

    public byte[] compress(byte[] data) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CompressorStreamFactory factory = new CompressorStreamFactory();
        CompressorOutputStream cos = null;
        try{
            cos = factory.createCompressorOutputStream(
                getName(), out
            );

            for(int i = 0; i < data.length; i++){
                cos.write(data[i]);
            }
        } catch(CompressorException e){
            throw new IOException(e);
        } finally{
            if(cos != null){
                cos.close();
            }
        }

        byte[] compressed = out.toByteArray();
        out.close();
        return compressed;
    }
}
