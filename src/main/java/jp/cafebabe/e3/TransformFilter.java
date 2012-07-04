package jp.cafebabe.e3;

/**
 * Filter class for selecting targets of transformation.
 * @author Haruaki Tamada
 */
public interface TransformFilter{
    /**
     * Returns true if given class is target of transformation.  If
     * not, this method returns false.  This method never throw any
     * exceptions.
     * @param className fully qualified class name separated with '/'
     * @return transformation flag. If true, given class will be
     * transformed. If false, given class is not transformed.
     */
    boolean isTarget(String className);
}
