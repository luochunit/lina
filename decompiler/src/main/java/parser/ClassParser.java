package parser;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 10:12 PM
 */
public class ClassParser implements Parser {
    final Class _class;

    public ClassParser(Class _class) {
        this._class = _class;
    }

    @Override
    public void parse(StringBuffer buffer) {
        Package _package = _class.getPackage();
        String simpleName = _class.getSimpleName();
        new PackageParser(_package).parse(buffer);
        buffer.append("public class ").append(simpleName).append(" {\n");
        Field[] fields = _class.getDeclaredFields();
        for (Field _field : fields) {
            new FieldParser(_field).parse(buffer);

        }
        buffer.append("}\n");
    }

}
