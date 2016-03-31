package cn.com.dormant.service.core.message.center;

import cn.com.dormant.service.core.message.MessageException;
import cn.com.dormant.service.core.message.base.*;
import cn.com.dormant.service.core.misc.CommonUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * <code>MsgListenerAgent<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/12/11
 * @version: 1.0
 */
public class MsgListenerAgent {

    public void listen() {
        BaseMessageCenter messageCenter = BaseMessageCenter.getInstance();

        TopicMsgManager msgManager = messageCenter.getMsgManager();
        if(msgManager == null) {
            return ;
        }

        List<TopicMessage> messageList = null;
        while (true) {
            try {
                messageList = msgManager.consume();
            } catch (MessageException e) {
                e.printStackTrace();
            }

            if(CommonUtils.isEmpty(messageList)) {
                continue;
            }

            String topicName = messageList.get(0).getTopic();
            MsgTopic topic = messageCenter.getTopic(topicName);
            if(topic == null) {
                System.out.println("Unregistered topic:" + topicName);
                continue;
            }

            List<MsgListener> listeners = topic.getListeners();
            if(listeners != null) {
                MsgEvent evt = new MsgEvent(topicName,messageList);
                for(MsgListener listener : listeners) {
                    listener.onReceive(evt);
                }
            }
        }
    }
}
