package jp.cafebabe.e3;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class E3ClassWriter extends ClassWriter {
    private ClassLoader loader;

    public E3ClassWriter(int flag) {
        super(flag);
    }

    public E3ClassWriter(ClassReader reader, int flag) {
        super(reader, flag);
    }

    public E3ClassWriter(int flag, ClassLoader loader) {
        super(flag);
        this.loader = loader;
    }

    public E3ClassWriter(ClassReader reader, int flag, ClassLoader loader) {
        super(reader, flag);
        this.loader = loader;
    }

    public void setLoader(ClassLoader loader){
        this.loader = loader;
    }

    public ClassLoader getLoader(){
        ClassLoader cl = getClass().getClassLoader();
        if(loader != null){
            cl = loader;
        }
        return cl;
    }

    protected String getCommonSuperClass(final String type1, final String type2){
        Class<?> c, d;
        ClassLoader classLoader = getLoader();
        try {
            c = Class.forName(type1.replace('/', '.'), false, classLoader);
            d = Class.forName(type2.replace('/', '.'), false, classLoader);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
        if (c.isAssignableFrom(d)) {
            return type1;
        }
        if (d.isAssignableFrom(c)) {
            return type2;
        }
        if (c.isInterface() || d.isInterface()) {
            return "java/lang/Object";
        } else {
            do {
                c = c.getSuperclass();
            } while (!c.isAssignableFrom(d));
            return c.getName().replace('.', '/');
        }
    }

}
