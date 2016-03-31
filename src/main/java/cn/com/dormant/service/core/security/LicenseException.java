package cn.com.dormant.service.core.security;

import cn.com.dormant.service.core.SysException;

/**
 * <code>LicenseException<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/8/14
 * @version: 1.0
 */
public class LicenseException extends SysException {
    public LicenseException(Throwable cause) {
        super(cause);
    }

    public LicenseException(String message) {
        super(message);
    }

    public LicenseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getRootMsg() {
        return super.getRootMsg();
    }
}
