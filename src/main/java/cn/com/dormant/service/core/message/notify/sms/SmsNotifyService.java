package cn.com.dormant.service.core.message.notify.sms;

import cn.com.dormant.service.core.message.notify.NotifyService;
import cn.com.dormant.service.core.message.notify.SysNotifyException;
import cn.com.dormant.service.core.misc.CommonUtils;

/**
 * <code>SmsNotifyService<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/7/8
 * @version: 1.0
 */
public abstract class SmsNotifyService implements NotifyService<SmsMessage> {
    private SmsConfig       config      = null;

    public SmsNotifyService() {
    }

    public SmsNotifyService(SmsConfig config) {
        this.config = config;
    }

    public SmsConfig getConfig() {
        return config;
    }

    public void setConfig(SmsConfig config) {
        this.config = config;
    }

    protected void validMessage(SmsMessage msg) throws SysNotifyException {
        if(msg == null) {
            throw new SysNotifyException("Invalid message: NULL");
        }

        String from = msg.getFrom();
        if(from == null || from.trim().isEmpty()) {
            throw new SysNotifyException("Invalid sender:" + from);
        }

        String[] to = msg.getTo();

        if(to == null || to.length == 0) {
            throw new SysNotifyException("No recipient mobile number");
        }

        if(CommonUtils.isEmpty(msg.getToStr())) {
            throw new SysNotifyException("No valid mobile to be sent");
        }
//        for(String t : to) {
//            if(t == null || t.trim().isEmpty()) {
//                throw new SysNotifyException("Invalid recipient mobile number:" + t);
//            }
//        }


    }

    protected void validConfig(SmsConfig config) throws SysNotifyException {
        /*
        Maybe need more complex validation logic
         */
        if(config == null) {
            throw new SysNotifyException("SMS configuration is NULL");
        }

        String server = config.getAddress();
        if(server == null || server.trim().isEmpty() ) {
            throw new SysNotifyException("No sms platform address");
        }

        Integer port = config.getPort();
        if(port == null || port <= 0) {
            throw new SysNotifyException("No sms server port configuration");
        }

        String user = config.getAccountId();
        if(user == null || user.trim().isEmpty()) {
            throw new SysNotifyException("No sms account configuration");
        }

        String password = config.getToken();
        if(password == null || password.trim().isEmpty()) {
            throw new SysNotifyException("No sms account token configuration");
        }
    }

}
