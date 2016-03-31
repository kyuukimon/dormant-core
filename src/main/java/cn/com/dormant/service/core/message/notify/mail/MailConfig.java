package cn.com.dormant.service.core.message.notify.mail;

/**
 * <code>MailConfig<code>
 *
 * @description: Mail Service related configuration
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/01/28
 * @version: 1.0
 */
public class MailConfig {
    private String      user              = "aguang_1@126.com";

    private String      password         = null;

    //POP3,IMAP,SMTP (For SSL: POP3S,IMAPS,SMTPS)
    private String      protocol         = "POP3";

    //imap.itsnow.com or smtp.itsnow.com
    private String      server           = "smtp.exmail.qq.com";

    private boolean    isSSL            = true;

    private Integer     port             = 465;

    //----------------------------------------------------------
    //Below is proxy related configuration
    //----------------------------------------------------------
    private boolean    useProxy         = false;

    //http, socks 4, socks 5
    private String      proxyType        = null;

    private String      proxyServer      = null;

    private String      proxyPort        = null;

    private String      proxyUser        = null;

    private String      proxyPassword   = null;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public boolean isSSL() {
        return isSSL;
    }

    public void setSSL(boolean isSSL) {
        this.isSSL = isSSL;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean isUseProxy() {
        return useProxy;
    }

    public void setUseProxy(boolean useProxy) {
        this.useProxy = useProxy;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    public String getProxyServer() {
        return proxyServer;
    }

    public void setProxyServer(String proxyServer) {
        this.proxyServer = proxyServer;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUser() {
        return proxyUser;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }
}
