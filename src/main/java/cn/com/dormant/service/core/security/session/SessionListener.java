/*****************************************************************************
 * HISTORY
 *
 * 2014/09/22   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security.session;

/**
 * <code>SessionListener<code>
 *
 * @description: This interface represent all handle methods for session listener
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/22
 * @version: 1.0
 */
public interface SessionListener {
    void sessionCreated(SessionEvent sessionEvent);

    void sessionDestroyed(SessionEvent sessionEvent);
}
