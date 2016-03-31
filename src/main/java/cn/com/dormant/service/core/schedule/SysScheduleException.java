package cn.com.dormant.service.core.schedule;

import cn.com.dormant.service.core.SysException;

/**
 * <code>SysScheduleException<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/11/19
 * @version: 1.0
 */
public class SysScheduleException extends SysException {
    public SysScheduleException(Throwable cause) {
        super(cause);
    }

    public SysScheduleException(String message) {
        super(message);
    }

    public SysScheduleException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getRootMsg() {
        return super.getRootMsg();
    }
}
