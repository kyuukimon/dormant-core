/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message;


import cn.com.dormant.service.core.message.base.MessageCenter;
import cn.com.dormant.service.core.message.base.MsgTopic;
import cn.com.dormant.service.core.message.base.BaseMessageCenter;
import cn.com.dormant.service.core.message.base.TopicMessage;

/**
 * <code>MessagePublisher<code>
 *
 * @description: One common for publish message
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class MessagePublisher {
    public static boolean useMQ() {
        MessageConfig config = BaseMessageCenter.getInstance().getConfig();
        return config.isUseMQ();
    }

    public static void publish(String topicName, String operation, Object obj) throws MessageException {
        if(!useMQ()) {
            System.out.println("No enable MQ support!");
            return ;
        }

        if(topicName == null || topicName.trim().isEmpty()) {
            throw new MessageException("Topic name is null or empty");
        }

        MessageCenter msgCenter = BaseMessageCenter.getInstance();
        MsgTopic topic = msgCenter.getTopic(topicName);
        if(topic == null) {
            throw new MessageException("No available topic for publishing message");
        }
        String tag = "";//MsgUtil.getMessageTagByTopic(operation, topic.getName());
        TopicMessage msg = new TopicMessage(tag, obj);
        topic.publish(msg);
    }


}
