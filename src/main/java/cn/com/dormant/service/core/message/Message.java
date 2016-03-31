/*****************************************************************************
 * HISTORY
 *
 * 2014/08/19   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message;

import java.io.Serializable;
import java.util.Date;

/**
 * <code>Message<code>
 *
 * @description: This class represent one message
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/08/19
 * @version: 1.0
 */
public class Message implements Serializable{
    public final static String MSG_TYPE_PROGRAM     = "PROGRAM_MSG";

    public final static String MSG_TYPE_EXCEPTION   = "EXCEPTION_MSG";

    public final static String MSG_TYPE_ERROR       = "ERROR_MSG";

    public final static String MSG_TYPE_BUSINESS    = "BUSINESS_MSG";
    /**
     * Message ID
     */
    private     int     id          = -1;

    /**
     * Message content
     */
    private     String  content     = "";

    /**
     * Creater who create this message
     */
    private     String  creator     = "NARCISSUS";

    /**
     * Message date
     */
    private     String  date         = "";

    /**
     * Message time
     */
    private     String  time         = "";

    /**
     * Message Type: PROGRAM_MESSAGE,EXCEPTION_MESSAGE,ERROR_MESSAGE,BUSINESS_MESSAGE
     */
    private     String  type         = MSG_TYPE_ERROR;

    public Message() {
        setDatetime();
    }

    public Message(String content) {
        this.content = content;
        setDatetime();
    }

    public Message(int id, String content) {
        this.id = id;
        this.content = content;

        setDatetime();
    }

    public Message(int id, String content, String creator, String type) {
        this.id = id;
        this.content = content;
        this.creator = creator;
        this.type = type;

        setDatetime();
    }

    public Message(String content, String creator, String type) {
        this.content = content;
        this.creator = creator;
        this.type = type;

        setDatetime();
    }

    private void setDatetime() {
        java.util.Calendar c=java.util.Calendar.getInstance();
        Date datetime = c.getTime();
        java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("yyyy-MM-dd");
        this.date = f.format(datetime);
        f=new java.text.SimpleDateFormat("hh:mm:ss");
        this.time = f.format(datetime);
    }

    @Override
    public String toString() {
        return new StringBuffer().append("{ ")
                .append("\"id\": ").append("\""+id+"\"")
                .append(",\"content\": ").append("\""+content+"\"")
                .append(",\"creator\": ").append("\""+creator+"\"")
                .append(",\"date\": ").append("\""+date+"\"")
                .append(",\"time\": ").append("\""+time+"\"")
                .append(",\"type\": ").append("\""+type+"\"")
                .append(" }").toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
