package cn.com.dormant.service.core.cache;

/**
 * Created by lenovo on 2015/4/16.
 */
public interface CacheLoader<T> {
    //retrieve()
    T load();
}
