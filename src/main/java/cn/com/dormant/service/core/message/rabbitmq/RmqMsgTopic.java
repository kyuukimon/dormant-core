/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.rabbitmq;

import cn.com.dormant.service.core.message.base.*;
import cn.com.dormant.service.core.message.MessageException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * <code>RmqMsgTopic<code>
 *
 * @description: One implementation of MsgTopic based on RabbitMQ
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class RmqMsgTopic extends BaseMsgTopic {
    private Channel channel = null;

    public RmqMsgTopic() {
        super();
        this.setListenerManager(new MsgListenerManager(this));
        this.setPublisher(new RmqMsgPublisher());
    }

    public RmqMsgTopic(String name) {
        super(name);
        this.setListenerManager(new MsgListenerManager(this));
        this.setPublisher(new RmqMsgPublisher());
    }

    @Override
    public void publish(TopicMessage msg) throws MessageException {
        wrapMessage(msg);
        MsgPublisher publisher = this.getPublisher();
        if(publisher == null) {
            String topic = null;
            if(msg != null) {
                topic = msg.getTopic();
            }
            throw new MessageException("No available publisher for topic [" + topic + "]");
        }

        publisher.publish(msg);
    }

    private void wrapMessage(TopicMessage msg) {
        if(msg == null) {
            return ;
        }
        msg.setTopic(this.getName());

        return;
    }
//    @Override
//    public MsgChannel buildChannel(MsgListener listener) throws MessageException{
//        RmqMsgChannel msgChannel = new RmqMsgChannel();
//        msgChannel.setTopic(this);
//        msgChannel.addListener(listener);
//
//        Channel channel = null;
//        try {
//            ConnectionManager.MsgConnection msgConn = ConnectionManager.getInstance().getConnection();
//            msgConn.use();
//            Connection connection = msgConn.get();
//            if(connection != null) {
//                channel = connection.createChannel();
//            } else {
//                throw new MessageException("Cannot get connection");
//            }
//        } catch (IOException e) {
//            throw new MessageException("Create AMQP channel failure", e);
//        }
//
//        msgChannel.setChannel(channel);
//
//        return msgChannel;
//    }


    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
