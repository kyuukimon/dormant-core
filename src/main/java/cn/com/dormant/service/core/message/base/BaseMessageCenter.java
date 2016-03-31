/*****************************************************************************
 * HISTORY
 *
 * 2014/12/12   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;


import cn.com.dormant.service.core.message.MessageConfig;
import cn.com.dormant.service.core.message.TopicFactory;
import cn.com.dormant.service.core.message.MessageException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <code>BaseMessageCenter<code>
 *
 * @description: This class is one base implementation for MessageCenter interface
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class BaseMessageCenter implements MessageCenter {
    private Map<String, MsgTopic> topics = new HashMap<String, MsgTopic>();

    private MessageConfig config = new MessageConfig();

    private TopicMsgManager msgManager = new TopicMsgManager();

    private final static BaseMessageCenter msgCenter = new BaseMessageCenter();

    public synchronized static BaseMessageCenter getInstance() {
        return msgCenter;
    }

    @Override
    public MsgTopic register(String topicName, MsgTopic topic) throws MessageException {
        if(topicName == null) {
            return null;
        }

        if(topics.containsKey(topicName)) {
            throw new MessageException("Existed same topic: " + topicName);
        }
        topics.put(topicName, topic);
        enable(topic);

        return topic;
    }

    @Override
    public MsgTopic register(String topicName) throws MessageException {
        if(topicName == null) {
            return null;
        }

        if(topics.containsKey(topicName)) {
            throw new MessageException("Existed same topic: " + topicName);
        }
        MsgTopic topic = TopicFactory.getInstance(config).createTopic(topicName);

        topics.put(topicName, topic);
        enable(topic);

        return topic;
    }

    @Override
    public void unregister(String topicName) {
        if(topicName == null) {
            return ;
        }

        topics.remove(topicName);
    }

    @Override
    public void enable(MsgTopic topic) {
        topic.enable();
    }

    @Override
    public void disable(MsgTopic topic) {
        topic.disable();
    }

    @Override
    public MsgTopic getTopic(String name) {
        return topics.get(name);
    }

    @Override
    public boolean isValidTopic(String name) {
        MsgTopic topic = getTopic(name);
        if(topic == null) {
            return false;
        }
        return topic.isValid();
    }

    @Override
    public MessageConfig getConfig() {
        return config;
    }

    public void setConfig(MessageConfig config) {
        this.config = config;
    }

    @Override
    public Set<String> getAllTopicNames() {
        return this.topics.keySet();
    }

    public TopicMsgManager getMsgManager() {
        return msgManager;
    }

    public void setMsgManager(TopicMsgManager msgManager) {
        this.msgManager = msgManager;
    }
}
