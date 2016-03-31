/*****************************************************************************
 * HISTORY
 *
 * 2014/08/19   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>SysInternalMessager<code>
 *
 * @description: Simple message handling
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/08/19
 * @version: 1.0
 */
public class SysInternalMessager {
    private List<Message> messages = new ArrayList<Message>();

    public void addMsg(Message message) {
        if(messages == null) {
            messages = new ArrayList<Message>();
        }

        this.messages.add(message);
    }

    public void addMsg(Message message,boolean clear) {
        if(clear) {
            this.clearMsg();
        }
        this.addMsg(message);
    }

    public void addMsg(int id, String message) {
        Message msg = new Message(id, message);

        this.addMsg(msg);
    }

    public void addMsg(int id, String message, boolean clear) {
        if(clear) {
            this.clearMsg();
        }
        this.addMsg(id, message);
    }

    public void addMsg(String message) {
        Message msg = new Message(message);
        this.addMsg(msg);
    }

    public void addMsg(String message, boolean clear) {
        if(clear) {
            this.clearMsg();
        }
        this.addMsg(message);
    }

    public void clearMsg() {
        if(this.messages == null) {
            return ;
        }

        this.messages.clear();
    }

    public List<Message> getAllMsgs() {
        return this.messages;
    }

    public String getMsgStr() {
        if(this.messages == null) {
            return null;
        }
        return messages.toString();
    }
}
