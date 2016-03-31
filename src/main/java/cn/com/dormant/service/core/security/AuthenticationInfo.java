/*****************************************************************************
 * HISTORY
 *
 * 2014/09/18   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security;

import java.util.List;

/**
 * <code>AuthenticationInformation<code>
 *
 * @description: This class represent authentication information which mainly include one or more principal
 * information and credential information for one authenticated subject
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/18
 * @version: 1.0
 */
public interface AuthenticationInfo {
    /**
     * Returns this Subject's application-wide uniquely identifying principal, or {@code null} if this
     * Subject is anonymous because it doesn't yet have any associated account data (for example,
     * if they haven't logged in).
     *
     * @return this Subject's application-specific unique identity.
     */
    Object getPrimaryPrincipal();

    /**
     * Returns all principals associated with the corresponding Subject.  Each principal is an identifying piece of
     * information useful to the application such as a username, or user id, a given name, etc - anything useful
     * to the application to identify the current <code>Subject</code>.
     *
     * The returned principals should NOT contain any credentials used to verify principals, such
     * as passwords, private keys, etc.  Those should be instead returned by getCredentials().
     *
     * @return all principals associated with the corresponding Subject.
     */
//    List<Object> getPrincipals();

    /**
     * Returns the credentials associated with the corresponding Subject.  A credential verifies one or more of the
     * principals associated with the Subject, such as a password or private key.  .
     *
     * @return the credentials associated with the corresponding Subject.
     */
//    List<Object> getCredentials();
}
