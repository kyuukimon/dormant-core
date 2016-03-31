/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;

import cn.com.dormant.service.core.message.MessageException;

/**
 * <code>MsgPublisher<code>
 *
 * @description: Topic utilize one message publisher tool to publish message.
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public interface MsgPublisher {

    void publish(TopicMessage message) throws MessageException;

}
