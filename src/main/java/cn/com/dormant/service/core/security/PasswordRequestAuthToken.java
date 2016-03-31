package cn.com.dormant.service.core.security;

import cn.com.dormant.service.core.security.RequestAuthToken;

/**
 * <code>PasswordRequestAuthToken<code>
 *
 * @description:
 * @author: Wang Jianguang(wangjianguang@126.com)
 * @creation: 2015/5/13
 * @version: 1.0
 */
public class PasswordRequestAuthToken extends RequestAuthToken {
    private String tenantId     = null;

    private String userId    = null;

    private String password     = null;


    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
