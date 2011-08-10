import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 11:27 PM
 */
public class Cache {
    private Map<String, String> store = new HashMap<String, String>();

    public void put(String key, String value) {
        this.store.put(key, value);
    }

    public String get(String key) {
        return this.store.get(key);
    }
}
