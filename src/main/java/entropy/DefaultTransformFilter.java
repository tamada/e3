package entropy;

public class DefaultTransformFilter{
    public boolean filter(String className){
        if(className.startsWith("java/") || className.startsWith("javax/") ||
           className.startsWith("org/objectweb/") || className.startsWith("org/apache/") ||
           className.startsWith("org/eclipse/") || className.startsWith("com/apple/") ||
           className.startsWith("org/junit/") || className.startsWith("entropy/") ||
           className.startsWith("sunw/") || className.startsWith("sun/")){
            return false;
        }
        return true;
    }
}