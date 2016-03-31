/*****************************************************************************
 * HISTORY
 *
 * 2014/09/22   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security.session;

/**
 * <code>DefaultSessionListener<code>
 *
 * @description: This class is one default session listener used to listen session status
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/22
 * @version: 1.0
 */
public class DefaultSessionListener implements SessionListener{
    @Override
    public void sessionCreated(SessionEvent sessionEvent) {

    }

    @Override
    public void sessionDestroyed(SessionEvent sessionEvent) {
        if(sessionEvent == null) {
            return ;
        }

        Session session = sessionEvent.getSession();
        SessionManager.getInstance().destroy(session);
    }
}
