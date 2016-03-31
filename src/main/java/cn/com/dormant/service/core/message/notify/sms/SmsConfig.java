package cn.com.dormant.service.core.message.notify.sms;

/**
 * <code>SmsConfig<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/7/8
 * @version: 1.0
 */
public class SmsConfig {
    //Sms platform provider
    private String  provider        = "";

    //SMS platform server address
    private String  address         = "";

    //SMS platform server port
    private Integer  port            = 0;

    //User account ID
    private String  accountId       = "";

    //User account's token
    private String  token           = "";

    //App ID
    private String  appId           = "";

    //if save sms history
    private boolean saveHistory     = false;

    //if enable sms
    private boolean enableSms       = false;

    //Shop Id
    private String shopId               = null;

    public SmsConfig() {
    }

    public SmsConfig(String address, Integer port, String accountId, String token) {
        this.address = address;
        this.port = port;
        this.accountId = accountId;
        this.token = token;
    }

    public SmsConfig(String address, Integer port, String accountId, String token, String appId) {
        this.address = address;
        this.port = port;
        this.accountId = accountId;
        this.token = token;
        this.appId = appId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public boolean isSaveHistory() {
        return saveHistory;
    }

    public void setSaveHistory(boolean saveHistory) {
        this.saveHistory = saveHistory;
    }

    public boolean isEnableSms() {
        return enableSms;
    }

    public void setEnableSms(boolean enableSms) {
        this.enableSms = enableSms;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
