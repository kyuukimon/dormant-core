/*****************************************************************************
 * HISTORY
 *
 * 2014/09/22   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security.session;

import cn.com.dormant.service.core.message.Message;
import cn.com.dormant.service.core.message.SysInternalEvent;

/**
 * <code>SessionEvent<code>
 *
 * @description: This class represent one session event including session information
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/22
 * @version: 1.0
 */
public class SessionEvent extends SysInternalEvent {
    private Session session = null;

    private Message message = null;

    public SessionEvent(Session session) {
        super(session);
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
