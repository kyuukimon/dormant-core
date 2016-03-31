package cn.com.dormant.service.core.cache;

import cn.com.dormant.service.core.SysException;

/**
 * <code>CacheException<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/4/22
 * @version: 1.0
 */
public class CacheException extends SysException {
    public CacheException(Throwable cause) {
        super(cause);
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }
}
