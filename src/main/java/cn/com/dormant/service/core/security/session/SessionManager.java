/*****************************************************************************
 * HISTORY
 *
 * 2014/09/22   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security.session;

import cn.com.dormant.service.core.security.Subject;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <code>SessionManager<code>
 *
 * @description: This class is used to provide one unify session manager interface
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/22
 * @version: 1.0
 */
public class SessionManager {
    private ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<String, Session>();

    private final static SessionManager sessionManager = new SessionManager();

    private SessionManager() {

    }

    public synchronized static SessionManager getInstance() {
        return sessionManager;
    }

    public Session startSession(String sessionId) {
        if (sessionId != null && sessions.containsKey(sessionId)) {
            return sessions.get(sessionId);
        }

//        Subject subject = new LegacySubject();
//        Session session = subject.getSession(true);
//        session.setAttribute("subject", subject);
        Session session = new DefaultSession();
        session.addListener(new DefaultSessionListener());
        sessions.put(session.getId(), session);

        return session;
    }

    public void destroy(String sessionId) {
        if (!sessions.containsKey(sessionId)) {
            return;
        }

        Session session = sessions.get(sessionId);
//        session.removeAttribute("subject");
        session.destroy();
        sessions.remove(sessionId);
    }


    public void destroy(Session session) {
        if (session == null) {
            return;
        }
        destroy(session.getId());
    }


    public Session getSession(String sessionId) {
        if (sessionId == null) {
            return null;
        }

        return sessions.get(sessionId);
    }

    public Session accessSession(String sessionId) {
        Session session = getSession(sessionId);
        if (session != null) {
            if (session.isExpired()) {
                return null;
            } else {
                session.touch();
            }
        }
        return session;
    }

    public Subject getSubject(Session session) {
        if (session != null) {
            return (Subject) session.getAttribute("subject");
        }
        return null;
    }

    public ConcurrentHashMap<String, Session> getSessions() {
        return sessions;
    }
//    HttpSessionListener
}
