package cn.com.dormant.service.core.message.rabbitmq;

import cn.com.dormant.service.core.message.MessageConverter;
import cn.com.dormant.service.core.message.base.*;
import cn.com.dormant.service.core.message.MessageException;
import com.rabbitmq.client.*;

/**
 * Created by Administrator on 2014/12/11.
 */
public class MessageConsumer {
    public final static String EXCHANGE_CLOUD_HOST = "exchange.cloud.host";

    public final static String ROUTING_KEY_CLOUD_HOST_ADD = "cloud.host.add";

    public final static String ROUTING_KEY_CLOUD_HOST_DELETE = "cloud.host.delete";

    public final static String ROUTING_KEY_CLOUD_HOST_UPDATE = "cloud.host.update";

    public final static String ROUTING_KEY_CLOUD_HOST_GET = "cloud.host.get";

    public final static String ROUTING_KEY_CLOUD_HOST_LIST = "cloud.host.list";

    public final static String BINDING_KEY_CLOUD_HOST = "cloud.host.*";

    public final static String BINDING_KEY_CLOUD_HOST_ADD = "cloud.host.add";

    public final static String BINDING_KEY_CLOUD_HOST_DELETE = "cloud.host.delete";

    public final static String QUEUE_CLOUD_HOST = "queue.cloud.host";

    public Connection getConnection() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        return connection;
    }

    public void consume(final String exchange, final String[] bindingKeys) throws Exception{
//        //For host add and delete related message
//        isOk = channel.queueDeclare(QUEUE_CLOUD_HOST,
//                false, false, true, null);
//        String addHostQueue = isOk.getQueue();
//        channel.exchangeBind(addHostQueue,EXCHANGE_CLOUD_HOST,BINDING_KEY_CLOUD_HOST_ADD);
//        channel.exchangeBind(addHostQueue,EXCHANGE_CLOUD_HOST,BINDING_KEY_CLOUD_HOST_DELETE);

        (new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    Connection connection = getConnection();
                    Channel channel = connection.createChannel();
                    channel.exchangeDeclare(exchange, "topic");

                    //for all host related message
//                    AMQP.Queue.DeclareOk isOk = channel.queueDeclare(QUEUE_CLOUD_HOST,
//                            false, false, true, null);
//                    String allHostQueue = isOk.getQueue();
//                    channel.queueBind(allHostQueue, EXCHANGE_CLOUD_HOST, BINDING_KEY_CLOUD_HOST);

                    String queueName = channel.queueDeclare().getQueue();
                    for(String key : bindingKeys) {
                        channel.queueBind(queueName,exchange,key);
                    }

                    QueueingConsumer consumer = new QueueingConsumer(channel);
                    channel.basicConsume(queueName, true, consumer);
                    while (true) {
                        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                        String message = new String(delivery.getBody());
                        String routingKey = delivery.getEnvelope().getRoutingKey();
                        System.out.println("Thread["+
                                Thread.currentThread().getId() +
                                "] Received '" + routingKey + "':'" + message + "'");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        })).start();

    }

    public void consumeEx(String listenerName,String topicName, String[] bindingKeys) {
        MessageCenter msgCenter = BaseMessageCenter.getInstance();
        MsgTopic topic = msgCenter.getTopic(topicName);
        if(topic == null) {
            System.out.println("Topic is not registered");
            return ;
        }

        RmqMsgListener listener = new RmqMsgListener(listenerName);
        listener.setBindingKeys(bindingKeys);
        listener = (RmqMsgListener)topic.register(listener.getName(),listener);

//        listener.listen(new MsgCallback() {
//            @Override
//            public void callback(Object input) {
//                TopicMessage message = null;
//                try {
//                    message = (TopicMessage) MessageConverter.Bytes2Object((byte[]) input);
//                }catch (MessageException e) {
//                    e.printStackTrace();
//                }
//
//
//                System.out.println("Thread[" +
//                        Thread.currentThread().getId() +
//                        "] Received" + ":'" + message + "'");
//            }
//        });

    }

    public static void main(String[] args) {
        System.out.println("Listening....");
        MessageConsumer consumer = new MessageConsumer();
        try {
            consumer.consume(EXCHANGE_CLOUD_HOST,new String[]{BINDING_KEY_CLOUD_HOST});
            consumer.consume(EXCHANGE_CLOUD_HOST,new String[]{BINDING_KEY_CLOUD_HOST_ADD,
                    BINDING_KEY_CLOUD_HOST_DELETE});

//            consumer.consumeEx("host_all",EXCHANGE_CLOUD_HOST,new String[]{BINDING_KEY_CLOUD_HOST});
//            consumer.consumeEx("host_add_delete",EXCHANGE_CLOUD_HOST,new String[]{BINDING_KEY_CLOUD_HOST_ADD,
//                                                                  BINDING_KEY_CLOUD_HOST_DELETE});
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
