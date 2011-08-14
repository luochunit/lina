import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 11:25 PM
 */
public class _CacheTest {

    @Test
    public void CacheComputedResult() {
        final Computable<String, String> duplicate
                = new Computable<String, String>() {
            @Override
            public String compute(String input) {
                return input + input;
            }
        };
        Cache cache = new Cache(duplicate);
        assertEquals("itemitem", cache.compute("item"));
        assertEquals("moneymoney", cache.compute("money"));
    }

    @Test
    public void OnlyComputeOnce() {
        final Computable<String, String> duplicate = mock(Computable.class);
        when(duplicate.compute("item")).thenReturn("itemitem");
        Cache cache = new Cache(duplicate);
        assertEquals("itemitem", cache.compute("item"));
        assertEquals("itemitem", cache.compute("item"));
        assertEquals("itemitem", cache.compute("item"));

        verify(duplicate, only()).compute("item");
    }


}
