package cn.com.dormant.service.core.message.notify.sms;

import cn.com.dormant.service.core.misc.CommonUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * <code>SmsMessage<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/7/8
 * @version: 1.0
 */
public class SmsMessage implements Serializable {
    //Sender mobile or name
    private String      from            = "NARCISSUS";

    //Recipients' mobile number list
    private String[]    to               = null;

    //Message type such as consume message and so on
    private Integer      type            = -1;

    //Message
    private String      message         = null;

    //Message's parameters
    private String[]      parameters      = null;

    //Response Message from SMS message sending
    private Object       responseMsg        = null;

    //Additional information for message
    private Map         additionalInfo      = null;

    public String getToStr() {
        String ret = "";

        if(to == null) {
            return ret;
        }

        for(int i = 0; i < to.length; i++) {
            if(CommonUtils.isEmpty(to[i]) || !CommonUtils.isMobile(to[i])) {
                continue;
            }
            ret += to[i];
            if(i < to.length - 1) {
                ret += ",";
            }
        }

        return ret;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String getParameterStr() {
        String ret = "";

        if(parameters == null) {
            return ret;
        }

        for(int i = 0; i < parameters.length; i++) {
            ret += parameters[i];
            if(i < parameters.length - 1) {
                ret += ",";
            }
        }

        return ret;
    }

    public Object getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(Object responseMsg) {
        this.responseMsg = responseMsg;
    }

    public static void main(String[] args){
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setTo(new String[]{"13123456676","13811223344","18622334455"});
        String msg[] = {"aa,bb,cc","aaa,bbb,ccc,ddd"};
        smsMessage.setParameters(msg);
        System.out.println(smsMessage.getToStr());
        System.out.println(smsMessage.getParameterStr());
    }

    public Map getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
