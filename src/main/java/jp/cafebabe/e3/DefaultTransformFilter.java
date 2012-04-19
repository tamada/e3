package jp.cafebabe.e3;

/**
 * Default filter for selecting targets of transformation.
 * This class has unmodifiable targets without compilation.
 *
 * @author Haruaki Tamada
 */
public final class DefaultTransformFilter implements TransformFilter {
    /**
     * This method returns that the given className is the target of
     * transformation or not.  If this method returns true, the class
     * of given class name is the target of transformation.  If false,
     * the class is not the target of transformation.
     * @param className class name for judge filtering
     * @return true when given class name is target of conversion,
     * otherwise false.
     */
    @Override
    public boolean filter(final String className) {
        if(className.startsWith("java/")
           || className.startsWith("javax/")
           || className.startsWith("org/objectweb/")
           || className.startsWith("org/apache/")
           || className.startsWith("org/eclipse/")
           || className.startsWith("com/apple/")
           || className.startsWith("org/junit/")
           || className.startsWith("jp/cafebabe/e3/")
           || className.startsWith("sunw/")
           || className.startsWith("sun/")) {
            return false;
        }
        return true;
    }
}