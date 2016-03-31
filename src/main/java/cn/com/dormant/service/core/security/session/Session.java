/*****************************************************************************
 * HISTORY
 *
 * 2014/09/19   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security.session;

import java.util.Collection;

/**
 * <code>Session<code>
 *
 * @description: This interface represent common operation for one request session
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/19
 * @version: 1.0
 */
public interface Session {
    /**
     * Returns the unique identifier assigned by the system upon session creation.
     *
     * @return The unique identifier assigned to the session upon creation.
     */
    String getId();

    /**
     * Returns the time the session was started; that is, the time the system created the instance.
     *
     * @return The time the system created the session.
     */
    long getCreationTime();

    /**
     * Returns the last time the application received a request or method invocation from the user associated
     * with this session.  Application calls to this method do not affect this access time.
     *
     * @return The time the user last interacted with the system.
     */
    long getLastAccessTime();


    /**
     * Returns the time in milliseconds that the session session may remain idle before expiring.
     *
     * A negative return value means the session will never expire.
     * A non-negative return value (0 or greater) means the session expiration will occur if idle for that
     * length of time.
     *
     * *Note:if you are used to the {@code HttpSession}'s {@code getMaxInactiveInterval()} method, the scale on
     * this method is different: This session use millisecond values for timeout whereas
     * {@code HttpSession.getMaxInactiveInterval} uses seconds.
     *
     * @return the time in milliseconds the session may remain idle before expiring.
     */
    long getMaxInactiveInterval();


    /**
     * Sets the time in milliseconds that the session may remain idle before expiring.
     *
     * A negative value means the session will never expire.</li>
     * A non-negative value (0 or greater) means the session expiration will occur if idle for that
     * length of time.
     *
     * *Note:if you are used to the {@code HttpSession}'s {@code getMaxInactiveInterval()} method, the scale on
     * this method is different: This sessions use millisecond values for timeout whereas
     * {@code HttpSession.getMaxInactiveInterval} uses seconds.
     *
     * @param maxInactiveInterval the time in milliseconds that the session may remain idle before expiring.
     */
    void setMaxInactiveInterval(long maxInactiveInterval);

    /**
     * Returns the host name or IP string of the host that originated this session, or {@code null}
     * if the host is unknown.
     *
     * @return the host name or IP string of the host that originated this session, or {@code null}
     *         if the host address is unknown.
     */
    String getHost();

    /**
     * Explicitly updates the {@link #getLastAccessTime() lastAccessTime} of this session to the current time when
     * this method is invoked.  This method can be used to ensure a session does not time out.
     *
     */
    void touch();

    /**
     * Explicitly stops (invalidates) this session and releases all associated resources.
     *
     */
    void destroy();

    /**
     * Returns the keys of all the attributes stored under this session.  If there are no
     * attributes, this returns an empty collection.
     *
     * @return the keys of all attributes stored under this session, or an empty collection if
     *         there are no session attributes.
     */
    Collection<Object> getAttributeKeys();

    /**
     * Returns the object bound to this session identified by the specified key.  If there is no
     * object bound under the key, {@code null} is returned.
     *
     * @param key the unique name of the object bound to this session
     * @return the object bound under the specified {@code key} name or {@code null} if there is
     *         no object bound under that name.
     */
    Object getAttribute(Object key);

    /**
     * Binds the specified {@code value} to this session, uniquely identified by the specifed
     * {@code key} name.  If there is already an object bound under the {@code key} name, that
     * existing object will be replaced by the new {@code value}.
     * <p/>
     * If the {@code value} parameter is null, it has the same effect as if
     * {@link #removeAttribute(Object) removeAttribute} was called.
     *
     * @param key   the name under which the {@code value} object will be bound in this session
     * @param value the object to bind in this session.
     */
    void setAttribute(Object key, Object value);

    /**
     * Removes (unbinds) the object bound to this session under the specified {@code key} name.
     *
     * @param key the name uniquely identifying the object to remove
     * @return the object removed or {@code null} if there was no object bound under the name
     *         {@code key}.
     */
    Object removeAttribute(Object key);

    /**
     * Determine if this session is expired
     *
     * @return
     */
    boolean isExpired();

    /**
     * Check if this session is valid
     *
     * @return
     */
    boolean isValid();

    void addListener(SessionListener listener);
}
