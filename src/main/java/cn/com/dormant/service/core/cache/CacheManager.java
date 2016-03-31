package cn.com.dormant.service.core.cache;

/**
 * <code>BaseDataManager<code>
 *
 * @description: This class is one base special manager for data feature related business
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/04/16
 * @version: 1.0
 */
public interface CacheManager {
    /**
     * Acquires the cache with the specified <code>name</code>.  If a cache does not yet exist with that name, a new one
     * will be created with that name and returned.
     *
     * @param name the name of the cache to acquire.
     * @return the Cache with the given name
     * @throws CacheException if there is an error acquiring the Cache instance.
     */
    public <K, V> Cache<K, V> getCache(String name) throws CacheException;

}
