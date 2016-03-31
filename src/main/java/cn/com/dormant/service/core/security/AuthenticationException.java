package cn.com.dormant.service.core.security;

import cn.com.dormant.service.core.CheckedSysException;
import cn.com.dormant.service.core.SysException;

/**
 * Created by lenovo on 2015/3/9.
 */
public class AuthenticationException extends SysException {
    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
