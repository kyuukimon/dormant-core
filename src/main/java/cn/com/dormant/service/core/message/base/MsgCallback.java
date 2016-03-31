/*****************************************************************************
 * HISTORY
 *
 * 2014/12/12   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;

/**
 * <code>MsgCallback<code>
 *
 * @description: When one listener receive message, need to call back message handler to handle message specially
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public interface MsgCallback {
    void callback(Object input);
}
