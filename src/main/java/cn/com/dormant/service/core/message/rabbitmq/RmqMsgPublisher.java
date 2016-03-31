/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.rabbitmq;

import cn.com.dormant.service.core.message.MessageConverter;
import cn.com.dormant.service.core.message.MessageException;
import cn.com.dormant.service.core.message.base.MsgPublisher;
import cn.com.dormant.service.core.message.base.MsgTopic;
import cn.com.dormant.service.core.message.base.TopicMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * <code>RmqMsgPublisher<code>
 *
 * @description: One implementation of MsgPublisher based on RabbitMQ
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class RmqMsgPublisher implements MsgPublisher {
    private RmqMsgTopic topic = null;

//    private Channel channel = null;

    public RmqMsgPublisher() {

    }

    public RmqMsgPublisher(RmqMsgTopic topic) {
        this.topic = topic;
    }

    @Override
    public void publish(TopicMessage message) throws MessageException {
        if(message == null) {
            throw new MessageException("NULL message");
        }
        Channel channel = topic.getChannel();
        String exchange = message.getTopic();
        String routingKey = message.getTag();
        try {
            if(channel == null) {
                ConnectionManager.MsgConnection msgConn = ConnectionManager.getInstance().getConnection();
                msgConn.use();
                Connection connection = msgConn.get();
                channel = connection.createChannel();
                msgConn.disuse();
            }
            channel.exchangeDeclare(exchange, "topic");
            String jsonStr = MessageConverter.Object2Json(message);
            byte[] bmsg = MessageConverter.Object2Bytes(jsonStr);
            channel.basicPublish(exchange,
                    routingKey,null,bmsg);

            topic.setChannel(channel);
        } catch (IOException e) {
            throw new MessageException("Publish message exception", e);
        }
        System.out.println("Send: [RoutingKey="+routingKey+",Message="+message+"]");

    }
}
