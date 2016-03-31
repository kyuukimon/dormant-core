package cn.com.dormant.service.core.message.center;

import cn.com.dormant.service.core.message.MessageException;
import cn.com.dormant.service.core.message.base.*;

/**
 * <code>CommonMsgTopic<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/12/9
 * @version: 1.0
 */
public class DefaultMsgTopic extends BaseMsgTopic {
    //The time of message keeping alive
    private long  keepAlive     = 0;

    public DefaultMsgTopic() {
        super();
        this.setPublisher(new DefaultMsgPublisher(this));
    }

    public DefaultMsgTopic(String name) {
        super(name);
        this.setPublisher(new DefaultMsgPublisher(this));
    }

    public DefaultMsgTopic(String name, MsgPublisher publisher) {
        super(name, publisher);
    }

    @Override
    public void publish(TopicMessage msg) throws MessageException {
        if(msg == null) {
            throw new MessageException("NULL message");
        }
        msg.setKeepAlive(keepAlive);

        MsgPublisher publisher = this.getPublisher();
        if(publisher == null) {
            String topic = null;
            if(msg != null) {
                topic = msg.getTopic();

            }
            throw new MessageException("No available publisher for topic [" + topic + "]");
        }
        msg.setTopic(this.getName());
        publisher.publish(msg);
    }

    public long getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(long keepAlive) {
        this.keepAlive = keepAlive;
    }

}
