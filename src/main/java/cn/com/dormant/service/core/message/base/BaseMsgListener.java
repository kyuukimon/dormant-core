/*****************************************************************************
 * HISTORY
 *
 * 2014/12/12   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;


/**
 * <code>BaseMsgListener<code>
 *
 * @description: This class is one base implementation for MsgListener interface
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public abstract class BaseMsgListener implements MsgListener{
    private String      name          = null;

    private MsgTopic      topic        = null;

    public BaseMsgListener() {

    }

    public BaseMsgListener(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MsgTopic getTopic() {
        return topic;
    }

    public void setTopic(MsgTopic topic) {
        this.topic = topic;
    }

}
