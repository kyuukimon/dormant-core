package cn.com.dormant.service.core.message.notify.mail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.com.dormant.service.core.message.notify.NotifyService;
import cn.com.dormant.service.core.message.notify.SysNotifyException;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <code>MailNotifyService<code>
 *
 * @description: Mail notification service implementation
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/01/28
 * @version: 1.0
 */
public class MailNotifyService implements NotifyService<MailMessage> {
    private MailConfig config = null;

    private Session session = null;
//
//    @Autowired
//    private SystemConfigAdapter systemConfigAdapter;

    public MailNotifyService() {

    }

    public MailNotifyService(MailConfig config) {
        this.config = config;
    }

    @Override
    public boolean notify(MailMessage msg) throws SysNotifyException {
//        this.config = systemConfigAdapter.getMailConfig();
        validMessage(msg);
        MimeMessage message = new MimeMessage(getSession());
        try {
            message.setFrom(getAddress(this.config.getUser()));
        } catch (MessagingException e) {
            throw new SysNotifyException("Set mail sender error",e);
        }

        try {
            message.setRecipients(Message.RecipientType.TO, getAddresses(msg.getTo()));
        } catch (MessagingException e) {
            throw new SysNotifyException("Set mail receiver error",e);
        }

        try {
            message.setSubject(msg.getSubject());
        } catch (MessagingException e) {
            throw new SysNotifyException("Set mail subject error",e);
        }

        try {
            message.setContent(msg.getContent(), "text/html;charset=utf-8");
        } catch (MessagingException e) {
            throw new SysNotifyException("Set mail content error",e);
        }
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            throw new SysNotifyException("Send mail error", e);
        }

        return true;
    }

    private Session getSession() throws SysNotifyException{
        if(session != null) {
            return session;
        }

        MailConfig config = getMailConfig();
        validMailConfig(config);

        Authenticator auth = new MailAuthenticator(config.getUser(), config.getPassword());

        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.host", config.getServer());
        props.put("mail.smtp.port", config.getPort());

        session = Session.getDefaultInstance(props, auth);

        return session;
    }

    private InternetAddress[] getAddresses(String[] strAdrList) throws SysNotifyException{
        InternetAddress[] addresses = null;
        if(strAdrList != null) {
            addresses = new InternetAddress[strAdrList.length];
            for(int i = 0; i < strAdrList.length; i++) {
                addresses[i] = getAddress(strAdrList[i]);
            }
        }
        return addresses;
    }
    private InternetAddress getAddress(String address) throws SysNotifyException{
        InternetAddress ret = null;
        try {
            ret = new InternetAddress(address);
        } catch (AddressException e) {
            throw new SysNotifyException("Invalid mail address:" + address);
        }

        return ret;
    }

    private void validMessage(MailMessage msg) throws SysNotifyException {
        if(msg == null) {
            throw new SysNotifyException("Invalid message: NULL");
        }

//        String from = msg.getFrom();
        String from = this.config.getUser();
        if(from == null || from.trim().isEmpty() || !checkEmail(from)) {
            throw new SysNotifyException("Invalid sender mail address:" + from);
        }

        String[] to = msg.getTo();

        if(to == null || to.length == 0) {
            throw new SysNotifyException("No recipient mail address");
        }

        for(String t : to) {
            if(t == null || t.trim().isEmpty() || !checkEmail(t)) {
                throw new SysNotifyException("Invalid recipient mail address:" + t);
            }
        }


    }

    private void validMailConfig(MailConfig config) throws SysNotifyException {
        /*
        Maybe need more complex validation logic
         */
        if(config == null) {
            throw new SysNotifyException("Mail configuration is NULL");
        }

        String server = config.getServer();
        if(server == null || server.trim().isEmpty() ) {
            throw new SysNotifyException("No mail server configuration");
        }

        Integer port = config.getPort();
        if(port == null || port <= 0) {
            throw new SysNotifyException("No mail server port configuration");
        }

        String user = config.getUser();
        if(user == null || user.trim().isEmpty()) {
            throw new SysNotifyException("No mail user configuration");
        }

        String password = config.getPassword();
        if(password == null || password.trim().isEmpty()) {
            throw new SysNotifyException("No mail password configuration");
        }
    }

    private MailConfig getMailConfig() {
        if(config != null) {
            return config;
        }
        config = new MailConfig();

        //TODO:Get from config file

        return config;
    }

    public boolean checkEmail(String email){
        String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void main(String[] args) {
        MailConfig config = new MailConfig();
        MailNotifyService notifyService = new MailNotifyService();

        MailMessage message = new MailMessage("aguang_1@126.com",
                new String[]{"aguang_1@126.com"},
//                             "bin_liu@itsnow.com",
//                             "jie_zhu@itsnow.com"},
                "WJG Notification Service Test",
                "Congratulation! You got one message from WJG notification service [Gavin] ;-)");
        try {
            notifyService.notify(message);
        }catch (SysNotifyException e) {
            e.printStackTrace();
        }
    }
}

class MailAuthenticator extends Authenticator{
    String user=null;
    String password=null;

    public MailAuthenticator(){

    }

    public MailAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(user, password);
    }
}

