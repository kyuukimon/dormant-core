package cn.com.dormant.service.core.cache;

/**
 * <code>EnhancedCache<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/5/13
 * @version: 1.0
 */
public class EnhancedCache<K,V> extends BaseCache<K,V> {
    private volatile boolean loadIfAbsent = true;

    public EnhancedCache(String name) {
        super(name);
    }

    public EnhancedCache(String name, boolean loadIfAbsent) {
        super(name);
        this.loadIfAbsent = loadIfAbsent;
    }

    public V get(K key, CacheLoader<V> loader) {
        V value = get(key);
        if(!loadIfAbsent) {
            return value;
        }
        synchronized (this) {
            if (value == null) {
                value = loader.load();
                put(key, value);
            }
        }

        return value;
    }

    public void put(K key, CacheLoader<V> loader) {
        if(loader == null) {
            throw new CacheException("No available data loader provided");
        }

        if(key == null) {
            throw new CacheException("Key is null");
        }
        put(key, loader.load());
    }

}
