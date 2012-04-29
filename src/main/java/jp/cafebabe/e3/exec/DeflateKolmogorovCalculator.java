package jp.cafebabe.e3.exec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

/**
 * This class calculate kolmogorov complexity by deflate compression
 * algorithm.
 * @author Haruaki Tamada
 */
public class DeflateKolmogorovCalculator extends AbstractKolmogorovCalculator{
    private static final int BUFFER_SIZE = 256;

    /**
     * Always returns &quot;deflate&quot;.
     * @return &quot;deflate&quot;
     */
    public String getName(){
        return "deflate";
    }

    public byte[] compress(byte[] data) throws IOException{
        Deflater deflater = new Deflater();

        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int read;
        while((read = deflater.deflate(buffer)) > 0){
            out.write(buffer, 0, read);
        }
        byte[] compressed = out.toByteArray();
        out.close();
        return compressed;
    }
}