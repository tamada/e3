package jp.cafebabe.e3.exec;

import java.io.IOException;

/**
 * Subclass of this class must implements {@link #getName
 * <code>getName</code>}, {@link #compress <code>compress</code>}, and
 * {@link #isAvailable <code>isAvailable</code>} methods.  {@link
 * #getSummary <code>getSummary</code>} method is declared as final
 * method, so that whole subclass returns summary in same format.
 * @author Haruaki Tamada
 */
public abstract class AbstractKolmogorovCalculator implements KolmogorovCalculator{
    public final String getSummary(byte[] data){
        try{
            byte[] compressed = compress(data);

            double rate = (double)compressed.length / data.length;

            return String.format("%s,%d,%d,%f", getName(), compressed.length, data.length, rate);
        } catch(IOException e){
            return String.format("%s,-,%d,-,%s", getName(), data.length, e.getMessage());
        }
    }

    public abstract String getName();

    public abstract byte[] compress(byte[] data) throws IOException;

    /**
     * Always returns true.
     */
    public boolean isAvailable(){
        return true;
    }
}