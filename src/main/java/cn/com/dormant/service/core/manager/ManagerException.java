package cn.com.dormant.service.core.manager;

import cn.com.dormant.service.core.CheckedSysException;

/**
 * <code>ManagerException<code>
 *
 * @description: Base business level manager exception class
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/10/29
 * @version: 1.0
 */
public class ManagerException extends CheckedSysException{
    public ManagerException(Throwable cause) {
        super(cause);
    }

    public ManagerException(String message) {
        super(message);
    }

    public ManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
