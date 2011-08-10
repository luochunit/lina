import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 9:19 PM
 */
public class _DecompilerTest {

    public static final String SOURCE_DIR = "/home/ringo/lina/decompiler/src/test/java/sample/";

    public static final String[] classes = new String[]{
            "EmptyClass", "OnePublicStaticStringFieldClass"
    };

    @Test
    public void EmptyClass() throws FileNotFoundException {
        Decompiler decompiler = new Decompiler();
        for (String className : classes) {
            String source = decompiler.generateSource("sample." + className);
            Scanner classFileScanner = new Scanner(
                    new File(SOURCE_DIR + className + ".java"));
            StringBuffer classFile = new StringBuffer();
            while (classFileScanner.hasNext()) {
                String line = classFileScanner.nextLine().trim();
                if (line.length() > 0) {
                    classFile.append(line).append("\n");
                }
            }
            assertEquals(classFile.toString(), source);
        }
    }
}
