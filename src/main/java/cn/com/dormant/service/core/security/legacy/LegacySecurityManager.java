///*****************************************************************************
// * HISTORY
// *
// * 2014/09/18   Wang Jianguang --Create file and initial implementation
// *
// ****************************************************************************/
//package cn.com.dormant.service.core.security.legacy;
//
//import cn.com.dormant.service.core.security.AuthToken;
//import cn.com.dormant.service.core.security.SecurityManager;
//import cn.com.dormant.service.core.security.session.Session;
//import cn.com.dormant.service.core.security.Subject;
//import cn.com.dormant.service.core.security.session.SessionManager;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.*;
//
///**
// * <code>LegacySecurityManager<code>
// *
// * @description: This class is one legacy security manager
// * @author: Wang Jianguang(aguang_1@126.com)
// * @creation: 2014/09/18
// * @version: 1.0
// */
//public class LegacySecurityManager implements SecurityManager {
//
//    private static Log log = LogFactory.getLog(LegacySecurityManager.class);
//
//    @Autowired
//    private HttpServletRequest request;
//
//    @Autowired
//    private SessionManager sessionManager;
//
//    @Override
//    public Session login(AuthToken authToken) {
//        String principal = (String)authToken.getPrincipal();
//        if(principal == null) {
//            return null;
//        }
//
//        String userId = principal;
//
//        List<Object> credentials = authToken.getCredentials();
//        if(credentials == null || credentials.size() == 0) {
//            return null;
//        }
//        String password = (String)(credentials.get(0));
//
//        //todo
//        Session session = sessionManager.startSession(null);
////        session.setAttribute(LegacySubject.KEY_AUTH_INFO, loginUser);
//        log.debug("Exit LegacySecurityManager.login()...");
//        return session;
//    }
//
//
//    @Override
//    public void logout(Subject subject) {
//        if(subject == null) {
//            return ;
//        }
//
//        String accessToken = request.getHeader("auth_token");
//        //todo: parse access token
//        sessionManager.destroy(accessToken);
//    }
//
//    @Override
//    public boolean isPermitted(Object principal, String permission) {
//        return false;
//    }
//
//    @Override
//    public boolean hasRole(Object principal, String roleId) {
//        Subject subject = getCurSubject();
//        return subject.hasRole(roleId);
//    }
//
//    @Override
//    public boolean hasRoles(Object principal, List<String> roleIds) {
//        Subject subject = getCurSubject();
//        return subject.hasRoles(roleIds);
//    }
//
//    public Subject getCurSubject( ) {
//        Subject subject = null;
//        HttpSession session = null;
//        Object loginUser = null;
//        try{
//            if(request != null) {
//                session = request.getSession();
//                loginUser = session.getAttribute("loginUser");
//            }
//
//        }catch (IllegalStateException e){
//            log.error("Call session.getAttribute(\"loginUser\") error",e);
//            return null;
//        }
//
//        //if cannot get login user information from session, it is possible that the request come from
//        //third-party application which send request in program and not from browser
//        if(loginUser == null) {
//            String accessToken = request.getHeader("auth_token");
//            //todo: parse access token
//            Session session1 = sessionManager.accessSession(accessToken);
//            subject = sessionManager.getSubject(session1);
//
//        } else {
//            subject = new LegacySubject();
//            Session innerSession = subject.getSession(true);
//            innerSession.setAttribute("AuthInfo", session.getAttribute("loginUser"));
//            Object isLogined = session.getAttribute("isLogin");
//            ((LegacySubject)subject).setAuthenticated(isLogined == null ? false : (Boolean)isLogined);
//        }
//        return subject;
//    }
//
//    @Override
//    public boolean isPermitted(String permission) {
//        Subject subject = getCurSubject();
//        if(subject == null) {
//            return false;
//        }
//        return subject.isPermitted(permission);
//    }
//
//    @Override
//    public boolean hasRole(String roleId) {
//        Subject subject = getCurSubject();
//        if(subject == null) {
//            return false;
//        }
//        return subject.hasRole(roleId);
//    }
//
//    @Override
//    public boolean hasRoles(List<String> roleIds) {
//        Subject subject = getCurSubject();
//        if(subject == null) {
//            return false;
//        }
//        return subject.hasRoles(roleIds);
//    }
//}
