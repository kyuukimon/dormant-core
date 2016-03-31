/*****************************************************************************
 * HISTORY
 *
 * 2014/12/12   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.rabbitmq;

import cn.com.dormant.service.core.message.base.BaseMsgListener;
import cn.com.dormant.service.core.message.base.MsgCallback;
import cn.com.dormant.service.core.message.base.MsgEvent;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <code>RmqMsgListener<code>
 *
 * @description: One implementation of MsgListener based on RabbitMQ
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class RmqMsgListener extends BaseMsgListener {
    private String[]    bindingKeys     = null;

    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    public RmqMsgListener(String name) {
        super(name);
    }

    @Override
    public void onReceive(final MsgEvent event) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                doListen(event);
            }
        });
    }

    private void doListen(MsgEvent event) {
//        MsgChannel channel = getChannel();
        Channel amqpChannel = ((RmqMsgTopic)this.getTopic()).getChannel();//((RmqMsgChannel)channel).getChannel();
        QueueingConsumer consumer = null;
        try {
            consumer = new QueueingConsumer(amqpChannel);
            amqpChannel.basicConsume(getName(), true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            QueueingConsumer.Delivery delivery = null;
            try {
                delivery = consumer.nextDelivery();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ShutdownSignalException e) {
                e.printStackTrace();
            } catch (ConsumerCancelledException e) {
                e.printStackTrace();
            }

            String routingKey = delivery.getEnvelope().getRoutingKey();
//            callback.callback(delivery.getBody());

            System.out.println("Thread[" +
                    Thread.currentThread().getId() +
                    "] Received '" + routingKey + "':'");
        }

    }

    public String[] getBindingKeys() {
        return bindingKeys;
    }

    public void setBindingKeys(String[] bindingKeys) {
        this.bindingKeys = bindingKeys;
    }
}
