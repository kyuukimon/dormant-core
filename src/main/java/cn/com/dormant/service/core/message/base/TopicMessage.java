/*****************************************************************************
 * HISTORY
 *
 * 2014/12/12   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;

import cn.com.dormant.service.core.message.Message;

import java.util.Map;

/**
 * <code>TopicMessage<code>
 *
 * @description: This class represent one topic message
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class TopicMessage extends Message {
    private String topic        = null;

    private String tag          = null;

    private Object contentEx   = null;

    //Some metadata information related to resource which are some key-values
    private Map<String, Object> metadata = null;

    private long  keepAlive     = 0;

    public TopicMessage() {
        super();
        setType(MSG_TYPE_BUSINESS);
    }

    public TopicMessage(String tag, String content) {
        super(content);
        this.tag = tag;
        setType(MSG_TYPE_BUSINESS);
    }

    public TopicMessage(String tag, Object contentEx) {
        super();
        this.tag = tag;
        this.contentEx = contentEx;
        setType(MSG_TYPE_BUSINESS);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getContentEx() {
        return contentEx;
    }

    public void setContentEx(Object contentEx) {
        this.contentEx = contentEx;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public long getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(long keepAlive) {
        this.keepAlive = keepAlive;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("{ ")
                .append("\"id\": ").append("\""+getId()+"\"")
                .append(",\"content\": ").append("\""+getContent()+"\"")
                .append(",\"creator\": ").append("\""+getCreator()+"."+getTopic()+"\"")
                .append(",\"date\": ").append("\""+getDate()+"\"")
                .append(",\"time\": ").append("\""+getTime()+"\"")
                .append(",\"type\": ").append("\""+getType()+"\"")
                .append(",\"topic\": ").append("\""+getTopic()+"\"")
                .append(",\"tag\": ").append("\""+getTag()+"\"")
                .append(",\"contentEx\": ").append("\""+getContentEx()+"\"")
                .append(" }").toString();
    }
}
