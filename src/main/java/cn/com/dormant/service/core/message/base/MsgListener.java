/*****************************************************************************
 * HISTORY
 *
 * 2014/12/12   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;

import cn.com.dormant.service.core.message.SysInternalEvent;

/**
 * <code>MsgListener<code>
 *
 * @description: In fact, One message listener is one message consumer. After listener is registered into one topic,
 * it can listen to receive message published by topic. Commonly, one listener need to start one special listening
 * thread which only to listen and receive message. For message handling, it should start one other thread for doing
 * message consume and handling.
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public interface MsgListener {
//    void listen(MsgCallback callback);

//    void listen();

    void onReceive(MsgEvent event);
}
