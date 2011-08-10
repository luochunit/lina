package parser;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 10:20 PM
 */
public class FieldParser implements Parser {

    private final Field _field;

    public FieldParser(Field _field) {
        this._field = _field;
    }

    @Override
    public void parse(StringBuffer buffer) {
        if (Modifier.isPublic(_field.getModifiers())) {
            buffer.append("public ");
        }
        if (Modifier.isStatic(_field.getModifiers())) {
            buffer.append("static ");
        }
        String fieldClassName = _field.getType().getSimpleName();
        buffer.append(fieldClassName).append(" ");
        String fieldName = _field.getName();
        buffer.append(fieldName).append(";\n");
    }
}
