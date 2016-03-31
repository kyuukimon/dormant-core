package cn.com.dormant.service.core.message.center;

import cn.com.dormant.service.core.message.MessageException;
import cn.com.dormant.service.core.message.base.*;

/**
 * <code>CommonMsgPublisher<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/12/9
 * @version: 1.0
 */
public class DefaultMsgPublisher implements MsgPublisher {
    private DefaultMsgTopic topic = null;

    public DefaultMsgPublisher(DefaultMsgTopic topic) {
        this.topic = topic;
    }

    @Override
    public void publish(TopicMessage message) throws MessageException {

        BaseMessageCenter systemMessageCenter = BaseMessageCenter.getInstance();
        TopicMsgManager msgManager = systemMessageCenter.getMsgManager();
        if(msgManager != null) {
            msgManager.join(message);
        }
    }
}
