package jp.cafebabe.e3.exec.kolmogorov;

import java.io.IOException;

/**
 * Subclass of this class must implements {@link #getName
 * <code>getName</code>}, {@link #compress <code>compress</code>}, and
 * {@link #isAvailable <code>isAvailable</code>} methods.
 * @author Haruaki Tamada
 */
public abstract class AbstractCalculator implements Calculator{
    public abstract String getName();

    public abstract byte[] compress(byte[] data) throws IOException;

    /**
     * Always returns true.
     */
    public boolean isAvailable(){
        return true;
    }
}
