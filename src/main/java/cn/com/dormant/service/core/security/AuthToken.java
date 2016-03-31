/*****************************************************************************
 * HISTORY
 *
 * 2014/09/18   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security;

import java.util.List;

/**
 * <code>AuthToken<code>
 *
 * @description: This class is used to include some authentication info submitted by some subject
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/18
 * @version: 1.0
 */
public interface AuthToken {
    /**
     * Get the account identity submitted during the authentication process.
     *
     * @return the account identity submitted during the authentication process.
     */
    Object getPrincipal();


    /**
     * Returns the credentials submitted by the user during the authentication process that verifies
     * the submitted {@link #getPrincipal() account identity}.
     *
     * @return the credential submitted by the user during the authentication process.
     */
    Object getCredential();
}
