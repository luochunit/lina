import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 11:25 PM
 */
public class _CacheTest {

    @Test
    public void PutGet() {
        Cache cache = new Cache();
        cache.put("item", "money");
        String item = cache.get("item");
        assertEquals("money", item);
    }


}
