/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;

import cn.com.dormant.service.core.message.MessageException;

import java.util.List;

/**
 * <code>MsgTopic<code>
 *
 * @description: A topic can provide special business message service which including message publish, manage message
 * listener, build message channel and so on. If topic need to provide message service , it must register in one message
 * center.
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public interface MsgTopic {
    void publish(TopicMessage msg) throws MessageException;

    List<MsgListener> getListeners();

    String getName();

    void enable();

    void disable();

    boolean isValid();

    MsgListener register(String name, MsgListener listener);

    void unregister(String name);


}
