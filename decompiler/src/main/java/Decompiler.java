import parser.ClassParser;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 9:23 PM
 */
public class Decompiler {

    public String generateSource(String classFullName) {
        StringBuffer source = new StringBuffer();
        try {
            Class _class = Class.forName(classFullName);
            new ClassParser(_class).parse(source);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return source.toString();
    }

}
