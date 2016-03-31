package cn.com.dormant.service.core.message.rabbitmq;


import cn.com.dormant.service.core.message.base.MsgTopic;
import cn.com.dormant.service.core.message.MessageException;
import cn.com.dormant.service.core.message.base.BaseMessageCenter;
import cn.com.dormant.service.core.message.base.MessageCenter;
import cn.com.dormant.service.core.message.base.TopicMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
/**
 * Created by Administrator on 2014/12/11.
 */
public class MessagePublisher {
    public final static String EXCHANGE_CLOUD_HOST = "exchange.cloud.host";

    public final static String ROUTING_KEY_CLOUD_HOST_ADD = "cloud.host.add";

    public final static String ROUTING_KEY_CLOUD_HOST_DELETE = "cloud.host.delete";

    public final static String ROUTING_KEY_CLOUD_HOST_UPDATE = "cloud.host.update";

    public final static String ROUTING_KEY_CLOUD_HOST_GET = "cloud.host.get";

    public final static String ROUTING_KEY_CLOUD_HOST_LIST = "cloud.host.list";

    public void publish(String routingKey, String message) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_CLOUD_HOST, "topic");

        channel.basicPublish(EXCHANGE_CLOUD_HOST,
                routingKey,null,message.getBytes());

        System.out.println("Send: [RoutingKey="+routingKey+",Message="+message+"]");
        connection.close();
    }

    public void buildEnv() {
        MessageCenter msgCenter = BaseMessageCenter.getInstance();
        MsgTopic topic = null;
        try {
            topic = msgCenter.register(EXCHANGE_CLOUD_HOST);
        } catch (MessageException e) {
            System.out.println("Failed to register topic:" + EXCHANGE_CLOUD_HOST);
            e.printStackTrace();
        }
    }

    public void publish() {
        MessageCenter msgCenter = BaseMessageCenter.getInstance();
        MsgTopic topic = null;
        try {
            topic = msgCenter.getTopic(EXCHANGE_CLOUD_HOST);

            TopicMessage msg = new TopicMessage(ROUTING_KEY_CLOUD_HOST_ADD, "1");
            topic.publish(msg);

            msg = new TopicMessage(ROUTING_KEY_CLOUD_HOST_DELETE, "2");
            topic.publish(msg);

            msg = new TopicMessage(ROUTING_KEY_CLOUD_HOST_GET, "3");
            topic.publish(msg);

            msg = new TopicMessage(ROUTING_KEY_CLOUD_HOST_UPDATE, "4");
            topic.publish(msg);

            msg = new TopicMessage(ROUTING_KEY_CLOUD_HOST_LIST, "5");
            topic.publish(msg);
        } catch (MessageException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("Send message");
        final MessagePublisher publisher = new MessagePublisher();
//        try {
//            publisher.publish(ROUTING_KEY_CLOUD_HOST_ADD, "1");
//            publisher.publish(ROUTING_KEY_CLOUD_HOST_DELETE, "2");
//            publisher.publish(ROUTING_KEY_CLOUD_HOST_GET, "3");
//            publisher.publish(ROUTING_KEY_CLOUD_HOST_UPDATE, "4");
//            publisher.publish(ROUTING_KEY_CLOUD_HOST_LIST, "5");
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Send completed");
//
        System.out.println("Build env...");
        publisher.buildEnv();

        System.out.println("Listening....");
        MessageConsumer consumer = new MessageConsumer();
        try {
            consumer.consumeEx("host_all",EXCHANGE_CLOUD_HOST,new String[]{BINDING_KEY_CLOUD_HOST});
            consumer.consumeEx("host_add_delete",EXCHANGE_CLOUD_HOST,new String[]{BINDING_KEY_CLOUD_HOST_ADD,
                    BINDING_KEY_CLOUD_HOST_DELETE});
        }catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                publisher.publish();
            }
        }).start();
    }

    public final static String BINDING_KEY_CLOUD_HOST = "cloud.host.*";

    public final static String BINDING_KEY_CLOUD_HOST_ADD = "cloud.host.add";

    public final static String BINDING_KEY_CLOUD_HOST_DELETE = "cloud.host.delete";

    public final static String QUEUE_CLOUD_HOST = "queue.cloud.host";

}
