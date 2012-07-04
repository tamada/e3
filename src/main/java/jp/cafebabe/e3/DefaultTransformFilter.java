package jp.cafebabe.e3;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is default filter for selecting targets of transformation.
 * This class has unmodifiable targets without compilation.
 *
 * @author Haruaki Tamada
 */
public final class DefaultTransformFilter implements TransformFilter {
    private List<String> targets = new ArrayList<String>();

    public DefaultTransformFilter(){
        targets.add("java/");
        targets.add("javax/");
        targets.add("sun/");
        targets.add("sunw/");
        targets.add("org/apache/");
        targets.add("org/objectweb/");
        targets.add("org/eclipse");
        targets.add("org/junit/");
        targets.add("com/apple/");
        targets.add("jp/cafebabe/e3/");
    }

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
    public boolean isTarget(String className){
        className = className.replace('.', '/');
        for(String packageName: targets){
            if(className.startsWith(packageName)){
                return false;
            }
        }
        return true;
    }
}