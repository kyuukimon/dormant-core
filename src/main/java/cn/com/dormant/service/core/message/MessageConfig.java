/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message;

/**
 * <code>MessageConfig<code>
 *
 * @description: Message related configuration
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class MessageConfig {
    public final static int MSG_PROVIDER_DEFAULT        = 0;

    public final static int MSG_PROVIDER_RABBITMQ       = 1;

    public final static int MSG_PROVIDER_SPRING         = 2;

    private boolean    useMQ          = true;

    private int      provider         = MSG_PROVIDER_DEFAULT;//"default";//default, rabbitmq, spring-rabbitmq, qpid

    private String      host            = "localhost";//"localhost";

    private int         port            = 5672;

    private String      userName        = "dormant";//"guest";

    private String      password        = "passw0rd";//"guest";

    private String      virtualHost     = "dormant";//"/";

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public boolean isUseMQ() {
        return useMQ;
    }

    public void setUseMQ(boolean useMQ) {
        this.useMQ = useMQ;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }
}
