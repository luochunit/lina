import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: ringo
 * Date: 8/10/11
 * Time: 11:27 PM
 */
public class Cache<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> store
            = new ConcurrentHashMap<A, Future<V>>();

    private final Computable<A, V> c;

    public Cache(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) {
        Future<V> f = store.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = store.putIfAbsent(arg, ft);
            if (f == null) {
                f = ft;
                ft.run();
            }
        }
        try {
            return f.get();
        } catch (InterruptedException e) {
            store.remove(arg, f);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
