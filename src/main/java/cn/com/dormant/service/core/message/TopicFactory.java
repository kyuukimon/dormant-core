/*****************************************************************************
 * HISTORY
 *
 * 2014/12/12   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message;

import cn.com.dormant.service.core.message.base.MsgTopic;
import cn.com.dormant.service.core.message.center.DefaultMsgTopic;
import cn.com.dormant.service.core.message.rabbitmq.RmqMsgTopic;

/**
 * <code>TopicFactory<code>
 *
 * @description: A factory used to get message topic
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class TopicFactory {
    private int msgProvider = 1;

    private static TopicFactory instance = null;

    private TopicFactory(int provider) {
        this.msgProvider = provider;
    }

    public synchronized static TopicFactory getInstance(MessageConfig config) {
        if(instance == null) {
            instance = new TopicFactory(config.getProvider());
        }
        return instance;
    }

    public MsgTopic createTopic(String topicName) {
        MsgTopic topic = null;
        if(msgProvider == MessageConfig.MSG_PROVIDER_DEFAULT) {
            topic = new DefaultMsgTopic(topicName);
        } else if(msgProvider == MessageConfig.MSG_PROVIDER_RABBITMQ) {
            topic = new RmqMsgTopic(topicName);
        } else if(msgProvider == MessageConfig.MSG_PROVIDER_SPRING) {

        }

        return topic;
    }


}
