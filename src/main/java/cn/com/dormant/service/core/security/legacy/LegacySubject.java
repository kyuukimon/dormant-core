///*****************************************************************************
// * HISTORY
// *
// * 2014/09/18   Wang Jianguang --Create file and initial implementation
// *
// ****************************************************************************/
//package cn.com.dormant.service.core.security.legacy;
//
//
//import cn.com.dormant.service.core.security.session.DefaultSession;
//import cn.com.dormant.service.core.security.session.Session;
//import cn.com.dormant.service.core.security.Subject;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * <code>LegacySubject<code>
// *
// * @description: This class is one legacy security subject which integrated with old version monitor system
// * @author: Wang Jianguang(aguang_1@126.com)
// * @creation: 2014/09/18
// * @version: 1.0
// */
//public class LegacySubject implements Subject {
//    public final static String KEY_AUTH_INFO = "AuthInfo";
//
//    private boolean isAuthenticated = false;
//
//    private DefaultSession session = null;
//
//    public LegacySubject() {
//        session = new DefaultSession();
//    }
//
//    public void setAuthenticated(boolean isAuthenticated) {
//        this.isAuthenticated = isAuthenticated;
//    }
//
//    @Override
//    public Object getPrimaryPrincipal() {
//        if(session == null) {
//            return null;
//        }
////        return ((session.getAttribute("AuthInfo"))).getUserId();
//        return null;
//        //todo
//    }
//
//    @Override
//    public List<Object> getPrincipals() {
//        Object obj = getPrimaryPrincipal();
//        if(obj == null) {
//            return null;
//        }
//
//        List principals = new ArrayList();
//        principals.add(obj);
//
//        return principals;
//    }
//
//    @Override
//    public boolean isPermitted(String permission) {
//        if(this.session == null) {
//            return false;
//        }
//
//        //todo
//        return false;
//    }
//
//    @Override
//    public boolean hasRole(String roleId) {
//        if(roleId == null || roleId.trim().isEmpty()) {
//            return false;
//        }
//
//        if(session == null) {
//            return false;
//        }
//        //todo
//        return false;
//    }
//
//    @Override
//    public boolean hasRoles(List<String> roleIds) {
//        if(roleIds == null || roleIds.size() == 0) {
//            return false;
//        }
//
//        if(session == null) {
//            return false;
//        }
//
//        //todo
//
//        return false;
//    }
//
//    @Override
//    public boolean isAuthenticated() {
//        return isAuthenticated;
//    }
//
//
//    @Override
//    public Session getSession(boolean create) {
//        if(session == null) {
//            if(create) {
//                session = new DefaultSession();
//            }
//        }
//
//        return session;
//    }
//
//
//}
