package cn.com.dormant.service.core.cache;

import cn.com.dormant.service.core.misc.CommonUtils;

import java.util.*;

/**
 * <code>BaseCache<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/4/22
 * @version: 1.0
 */
public class BaseCache<K, V> implements Cache<K, V> {
    /**
     * Backing instance.
     */
    private final Map<K, V> map = new HashMap<K, V>();

    /**
     * The name of this cache.
     */
    private final String name;

    public BaseCache(String name){//}, Map<K, V> container) {
        if (name == null) {
            throw new CacheException("Cache name cannot be null.");
        }
//        if (container == null) {
//            throw new CacheException("Cache container cannot be null.");
//        }
        this.name = name;
//        this.map = container;
    }

    public V get(K key) throws CacheException {
        return map.get(key);
    }

    public V put(K key, V value) throws CacheException {
        return map.put(key, value);
    }

    public V remove(K key) throws CacheException {
        return map.remove(key);
    }

    public void clear() throws CacheException {
        map.clear();
    }

    public int size() {
        return map.size();
    }

    public Set<K> keys() {
        Set<K> keys = map.keySet();
        if (!keys.isEmpty()) {
            return Collections.unmodifiableSet(keys);
        }
        return Collections.emptySet();
    }

    public Collection<V> values() {
        Collection<V> values = map.values();
        if (!CommonUtils.isEmpty(values)) {
            return Collections.unmodifiableCollection(values);
        }
        return Collections.emptySet();
    }

    public String toString() {
        return new StringBuilder("BaseCache '")
                .append(name).append("' (")
                .append(map.size())
                .append(" entries)")
                .toString();
    }
}
