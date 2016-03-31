package cn.com.dormant.service.core.message.notify.mail;

import java.io.Serializable;

/**
 * <code>MailMessage<code>
 *
 * @description: Mail message entity storing message related information
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/01/28
 * @version: 1.0
 */
public class MailMessage implements Serializable{
    private String      from            = null;

    private String[]    to               = null;

    private String      subject         = null;

    private String      content         = null;

    public MailMessage() {

    }

    public MailMessage(String from, String[] to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
