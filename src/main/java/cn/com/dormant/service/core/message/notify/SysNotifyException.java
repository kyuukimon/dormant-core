package cn.com.dormant.service.core.message.notify;

import cn.com.dormant.service.core.CheckedSysException;
import cn.com.dormant.service.core.SysException;

/**
 * <code>SysNotifyException<code>
 *
 * @description: Mail notification related exception
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/01/28
 * @version: 1.0
 */
public class SysNotifyException extends SysException {
    public SysNotifyException(Throwable cause) {
        super(cause);
    }

    public SysNotifyException(String message) {
        super(message);
    }

    public SysNotifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
