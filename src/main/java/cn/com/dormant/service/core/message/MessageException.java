/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message;

import cn.com.dormant.service.core.CheckedSysException;

/**
 * Created by Administrator on 2014/12/12.
 */
public class MessageException extends CheckedSysException {

    public MessageException(Throwable cause) {
        super(cause);
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
