package jp.cafebabe.e3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This is filter clas for selecting targets of transformation.
 * This class has unmodifiable targets without compilation.
 *
 * @author Haruaki Tamada
 */
public final class CsvTransformFilter implements TransformFilter {
    private List<String> targets = new ArrayList<String>();

    public CsvTransformFilter(String file) throws IOException{
        readFile(file);
    }

    private void readFile(String file) throws IOException{
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line = null;
            while((line = in.readLine()) != null){
                if(!line.startsWith("#")){
                    String[] names = line.split(", *");
                    for(String name: names){
                        targets.add(name.replace('.', '/'));
                    }
                }
            }
        } finally{
            if(in != null){
                in.close();
            }
        }
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
    public boolean isTarget(final String className) {
        for(String packageName: targets){
            if(className.startsWith(packageName)){
                return false;
            }
        }
        return true;
    }
}