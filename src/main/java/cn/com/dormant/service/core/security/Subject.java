/*****************************************************************************
 * HISTORY
 *
 * 2014/09/18   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security;

import cn.com.dormant.service.core.security.session.Session;

import java.util.List;

/**
 * <code>Subject<code>
 *
 * @description:
 * A {@code Subject} represents state and security operations for a single application user.
 * These operations include authentication (login/logout), authorization (access control), and
 * so on.
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/18
 * @version: 1.0
 */
public interface Subject {
    /**
     * Returns this Subject's application-wide uniquely identifying principal, or {@code null} if this
     * Subject is anonymous because it doesn't yet have any associated account data (for example,
     * if they haven't logged in).
     *
     * @return this Subject's application-specific unique identity.
     */
    Object getPrimaryPrincipal();

    /**
     * Returns all principals associated with this Subject.  Each principal is an identifying piece of
     * information useful to the application such as a username, or user id, a given name, etc - anything useful
     * to the application to identify the current <code>Subject</code>.
     *
     * The returned principals should NOT contain any credentials used to verify principals, such
     * as passwords, private keys, etc.  Those should be instead returned by getCredentials().
     *
     * @return all principals associated with this Subject.
     */
//    List<Object> getPrincipals();

    /**
     * Returns {@code true} if this Subject is permitted to perform an action or access a resource summarized by the
     * specified permission.
     *
     * @param permission the permission that is being checked.
     * @return true if this Subject is permitted, false otherwise.
     */
    boolean isPermitted(String permission);

    /**
     * Returns {@code true} if this Subject has the specified role, {@code false} otherwise.
     *
     * @param roleId the application-specific role identifier (usually a role id or role name).
     * @return {@code true} if this Subject has the specified role, {@code false} otherwise.
     */
    boolean hasRole(String roleId);

    /**
     * Checks if this Subject has the specified roles, returning a boolean array indicating
     * which roles are associated.
     *
     * @param roleIds the application-specific role identifiers to check (usually role ids or role names).
     * @return a boolean array where indices correspond to the index of the
     *         roles in the given identifiers.  A true value indicates this Subject has the
     *         role at that index.  False indicates this Subject does not have the role at that index.
     */
    boolean hasRoles(List<String> roleIds);

    /**
     * Returns {@code true} if this Subject/user proved their identity <em>during their current session</em>
     * by providing valid credentials matching those known to the system, {@code false} otherwise.
     *
     * @return {@code true} if this Subject proved their identity during their current session
     *         by providing valid credentials matching those known to the system, {@code false} otherwise.
     */
    boolean isAuthenticated();

    /**
     * Returns the application {@code Session} associated with this Subject.  Based on the boolean argument,
     * this method functions as follows:
     * <ul>
     * <li>If there is already an existing session associated with this {@code Subject}, it is returned and
     * the {@code create} argument is ignored.</li>
     * <li>If no session exists and {@code create} is {@code true}, a new session will be created, associated with
     * this {@code Subject} and then returned.</li>
     * <li>If no session exists and {@code create} is {@code false}, {@code null} is returned.</li>
     * </ul>
     *
//     * @param create boolean argument determining if a new session should be created or not if there is no existing session.
     * @return the application {@code Session} associated with this {@code Subject} or {@code null} based
     *         on the above described logic.
     */
    Session getSession();

    /**
     * Returns <tt>true</tt> if current Subject (getCurSubject()) is permitted to perform an action or access a resource
     * summarized by the specified permission.
     *
     //     * @param subjectPrincipal the application-specific subject/user identifier.
     * @param resourceType resource type
     * @param permission       the permission that is being checked.
     * @param resourceInstance instances of special resource type
     * @return true if the corresponding Subject/user is permitted, false otherwise.
     */
    boolean isPermitted(String resourceType, String permission, String resourceInstance);
}
