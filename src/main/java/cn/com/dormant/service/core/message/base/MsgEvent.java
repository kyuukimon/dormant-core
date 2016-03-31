package cn.com.dormant.service.core.message.base;

import cn.com.dormant.service.core.message.SysInternalEvent;

import java.util.List;

/**
 * <code>${CLASS}<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/12/11
 * @version: 1.0
 */
public class MsgEvent extends SysInternalEvent<String> {
    private List<TopicMessage> messages = null;

    public MsgEvent(String topic) {
        super(topic);
    }

    public MsgEvent(String source, List<TopicMessage> messages) {
        super(source);
        this.messages = messages;
    }

    public List<TopicMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<TopicMessage> messages) {
        this.messages = messages;
    }
}
