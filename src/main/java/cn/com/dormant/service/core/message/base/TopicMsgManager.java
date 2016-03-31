package cn.com.dormant.service.core.message.base;

import cn.com.dormant.service.core.message.MessageException;
import cn.com.dormant.service.core.message.base.*;
import cn.com.dormant.service.core.message.center.DefaultMsgTopic;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.*;

/**
 * <code>MessageManager<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/12/9
 * @version: 1.0
 */
public class TopicMsgManager {
    private volatile boolean keepAliveMsg = false;

    private ConcurrentMap<String,List<TopicMessage>> messagePool
                = new ConcurrentHashMap<String, List<TopicMessage>>();

    private BlockingQueue<String> tipsPool = new LinkedBlockingQueue<String>();

    protected ConcurrentMap<String,List<TopicMessage>> getMessagePool() {
        return messagePool;
    }

    public void join(TopicMessage message) throws MessageException{
        if(message == null) {
            return ;
        }

        String topic = message.getTopic();
        Map<String,List<TopicMessage>> messagePool = getMessagePool();
        List<TopicMessage> topicMsgList = messagePool.get(topic);
        if(topicMsgList == null) {
            topicMsgList = new ArrayList<TopicMessage>();
        }

        MessageCenter msgCenter = BaseMessageCenter.getInstance();
        MsgTopic msgTopic = msgCenter.getTopic(topic);
        if (msgTopic == null) {
            throw new MessageException("No available topic for publishing message");
        }
        long keepAlive = ((DefaultMsgTopic) msgTopic).getKeepAlive();

        for (int i = topicMsgList.size() - 1; i >= 0; i--) {
            if(keepAliveMsg) {
                TopicMessage msg = topicMsgList.get(i);
                String date = msg.getDate();
                String time = msg.getTime();
                java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    Date dt = f.parse(date + " " + time);
                    Calendar c = Calendar.getInstance();
                    c.setTime(dt);
                    long tm = c.getTimeInMillis();
                    long ctm = System.currentTimeMillis();
                    if (ctm - tm > keepAlive) {
                        topicMsgList.remove(i);
                    }

                } catch (ParseException e) {
                    throw new MessageException(e);
                }
            } else {
                topicMsgList.remove(i);
            }
        }

        topicMsgList.add(message);
        messagePool.put(topic, topicMsgList);
        try {
            tipsPool.put(topic);
        } catch (InterruptedException e) {
            throw new MessageException(e);
        }

    }

    public List<TopicMessage> consume() throws MessageException{
        List<TopicMessage> topicMessages = null;
        try{
            String topic = this.tipsPool.take();
            List<TopicMessage> originMsgs = messagePool.get(topic);
//            topicMessages = new ArrayList<TopicMessage>(originMsgs.size());
//            Collections.copy(topicMessages,originMsgs);
            TopicMessage[] array = Arrays.copyOf(originMsgs.toArray(), originMsgs.size(), TopicMessage[].class);
            topicMessages = Arrays.asList(array);
        } catch (InterruptedException e) {
            throw new MessageException(e);
        }

        return topicMessages;
    }

    public void clearMsg(String topic) {
        List<TopicMessage> messages = messagePool.get(topic);

        synchronized (messages) {
            messages.clear();
        }

        messagePool.putIfAbsent(topic, null);
    }

    public void clearAllMsg() {
        Set<String> keys = messagePool.keySet();
        Iterator<String> iter = keys.iterator();
        while (iter.hasNext()) {
            String topic = iter.next();
            clearMsg(topic);
        }
    }

    public boolean isKeepAliveMsg() {
        return keepAliveMsg;
    }

    public void setKeepAliveMsg(boolean keepAliveMsg) {
        this.keepAliveMsg = keepAliveMsg;
    }

    public Set<String> getCurrentTopics() {
        return messagePool.keySet();
    }

    public List<TopicMessage> getTopicMsg(String topic) {
        return messagePool.get(topic);
    }
}
