/*****************************************************************************
 * HISTORY
 *
 * 2014/09/18   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security;

import java.util.List;

/**
 * <code>LegacySecurityManager<code>
 *
 * @description: This class is one facade interface for security manager
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/18
 * @version: 1.0
 */
public interface SecurityManager {

    /**
     * Logs in the specified Subject using the given {@code authToken}, returning an special Subject
     * instance reflecting the authenticated state if successful or throwing Authentication Exception if it is
     * not.
     *
     *@param authToken the token representing the Subject's principal(s) and credential(s)
     * @return the subject instance reflecting the authenticated state after a successful attempt
     */
    Subject login(AuthToken authToken);

    /**
     * Logs out the specified Subject from the system.
     *
     * @param subject the subject to log out.
     */
    void logout(Subject subject);

    /**
     * Returns <tt>true</tt> if the corresponding subject/user is permitted to perform an action or access a resource
     * summarized by the specified permission.
     *
     * @param principal the application-specific subject/user identifier.
     * @param permission       the permission that is being checked.
     * @return true if the corresponding Subject/user is permitted, false otherwise.
     */
    boolean isPermitted(Object principal, String permission);

    /**
     * Returns {@code true} if this Subject has the specified role, {@code false} otherwise.
     *
     * @param roleId the application-specific role identifier (usually a role id or role name).
     * @return {@code true} if this Subject has the specified role, {@code false} otherwise.
     */
    boolean hasRole(Object principal, String roleId);

    /**
     * Checks if this Subject has the specified roles, returning a boolean array indicating
     * which roles are associated.
     *
     * @param roleIds the application-specific role identifiers to check (usually role ids or role names).
     * @return a boolean array where indices correspond to the index of the
     *         roles in the given identifiers.  A true value indicates this Subject has the
     *         role at that index.  False indicates this Subject does not have the role at that index.
     */
    boolean hasRoles(Object principal, List<String> roleIds);

    /**
     * Returns <tt>true</tt> if current Subject (getCurSubject()) is permitted to perform an action or access a resource
     * summarized by the specified permission.
     *
     //     * @param subjectPrincipal the application-specific subject/user identifier.
     * @param permission       the permission that is being checked.
     * @return true if the corresponding Subject/user is permitted, false otherwise.
     */
    boolean isPermitted(String permission);

    /**
     * Returns {@code true} if current Subject (getCurSubject()) has the specified role, {@code false} otherwise.
     *
     * @param roleId the application-specific role identifier (usually a role id or role name).
     * @return {@code true} if this Subject has the specified role, {@code false} otherwise.
     */
    boolean hasRole(String roleId);

    /**
     * Checks if current Subject (getCurSubject()) has the specified roles, returning a boolean array indicating
     * which roles are associated.
     *
     * @param roleIds the application-specific role identifiers to check (usually role ids or role names).
     * @return a boolean array where indices correspond to the index of the
     *         roles in the given identifiers.  A true value indicates this Subject has the
     *         role at that index.  False indicates this Subject does not have the role at that index.
     */
    boolean hasRoles(List<String> roleIds);

    /**
     * Get current subject logged in system
     *
     * @return
     */
    Subject getCurSubject();

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
