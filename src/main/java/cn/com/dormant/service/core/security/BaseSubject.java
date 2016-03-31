package cn.com.dormant.service.core.security;

import cn.com.dormant.service.core.security.session.Session;

import java.util.List;

/**
 * <code>DefaultSubject<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/4/22
 * @version: 1.0
 */
public class BaseSubject implements Subject {
    private boolean isAuthenticated = false;

    private AuthenticationInfo authenticationInfo = null;

    private Session session = null;

    public BaseSubject() {

    }

    public BaseSubject(Session session) {
        this.session = session;
    }

    /**
     * Return subject's authentication information. if no
     *
     * @return
     */
    public AuthenticationInfo getAuthenticationInfo() {
//        if(session == null) {
//            return null;
//        }
//
//        AuthenticationInfo authenticationInfo =
//                (AuthenticationInfo)session.getAttribute(SecurityConstants.SEC_AUTH_INFO);

        return authenticationInfo;
    }

    public void setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }

    @Override
    public Object getPrimaryPrincipal() {
        if(this.authenticationInfo != null) {
            return this.authenticationInfo.getPrimaryPrincipal();
        }
        return null;
    }

//    @Override
//    public List<Object> getPrincipals() {
//        if(this.authenticationInfo != null) {
//            return this.authenticationInfo.getPrincipals();
//        }
//        return null;
//    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public Session getSession() {
        return this.session;
    }

    @Override
    public boolean isPermitted(String permission) {
        return false;
    }

    @Override
    public boolean hasRole(String roleId) {
        return false;
    }

    @Override
    public boolean hasRoles(List<String> roleIds) {
        return false;
    }

    @Override
    public boolean isPermitted(String resourceType, String permission, String resourceInstance) {
        return false;
    }
}
