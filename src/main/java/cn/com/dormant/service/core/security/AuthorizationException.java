package cn.com.dormant.service.core.security;

import cn.com.dormant.service.core.CheckedSysException;
import cn.com.dormant.service.core.SysException;

/**
 * Created by lenovo on 2015/3/9.
 */
public class AuthorizationException extends SysException {
    public AuthorizationException(Throwable cause) {
        super(cause);
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
