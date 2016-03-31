package cn.com.dormant.service.core.security;

import cn.com.dormant.service.core.security.session.Session;
import cn.com.dormant.service.core.security.session.SessionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.*;

/**
 * <code>BaseSecurityManager<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/4/22
 * @version: 1.0
 */
public abstract class BaseSecurityManager implements SecurityManager {
    private static Log log = LogFactory.getLog(BaseSecurityManager.class);

    private SessionManager sessionManager = SessionManager.getInstance();

    @Override
    public Subject login(AuthToken authToken) {
        String principal = (String)authToken.getPrincipal();
        if(principal == null) {
            return null;
        }

//        List<Object> credentials = authToken.getCredentials();
//        if(credentials == null || credentials.size() == 0) {
//            return null;
//        }

        AuthenticationInfo authInfo = doLogin(authToken);

        if(authInfo == null) {
            return null;
        }

        Session session = startSession();//sessionManager.startSession(null);
//        session.setAttribute(SecurityConstants.SEC_AUTH_INFO, authInfo);

        BaseSubject subject = createSubject(session);//new BaseSubject(session);
        subject.setAuthenticationInfo(authInfo);
        subject.setAuthenticated(true);

        return subject;
    }

    abstract public AuthenticationInfo doLogin(AuthToken authToken);

    @Override
    public void logout(Subject subject) {
        if(subject == null) {
            return ;
        }

        doLogout(subject);

        sessionManager.destroy(subject.getSession());
    }

    abstract public void doLogout(Subject subject);

    protected BaseSubject createSubject(Session session) {
        return new BaseSubject(session);
    }

    protected Session startSession() {
        return sessionManager.startSession(null);
    }
}
