package cn.com.dormant.service.core.security.rbac.services;

import cn.com.dormant.service.core.security.rbac.objects.Permission;
import cn.com.dormant.service.core.security.rbac.objects.Role;
import cn.com.dormant.service.core.security.rbac.objects.UserAccount;

import java.util.List;


/**
 * <code>IAccountManagementService</code> 
 * 
 * @creation 2011-07-27
 * @author 	 Gavin Wang(aguang_1@126.com)
 * 
 *  <TABLE>
 *      <TR><TD>DATE</TD><TD>Defect</TD><TD>who</TD><TD>Change Description</TD></TR>
 *      <TR><TD>2011-07-27</TD><TD>None</TD><TD>Gavin Wang(aguang_1@126.com)</TD><TD>Initial create</TD></TR>
 *  </TABLE>
 */
public interface IUserMgtService {
	void createUserAccount(UserAccount account) throws UserMgtServiceException;
	
	void deleteUserAccount(String userId) throws UserMgtServiceException;
	
	void updateUserAccount(UserAccount account) throws UserMgtServiceException;
	
	List<UserAccount> getAllUserAccounts() throws UserMgtServiceException;
	
	UserAccount getUserAccount(String userId) throws UserMgtServiceException;
	
	boolean disableUserAccount(String userId) throws UserMgtServiceException;
	
	boolean enableUserAccount(String userId) throws UserMgtServiceException;
	
	boolean isUserAccountEnabled(String userId) throws UserMgtServiceException;
	
	boolean isPasswordExpired(String userId) throws UserMgtServiceException;
	
	boolean changePassword(String userID, String oldPwd, String newPwd, String confirmNewPwd) throws UserMgtServiceException;
	
	boolean validatePassword(String userId, String password) throws UserMgtServiceException;
	
	boolean validateUserAccount(UserAccount userAccount) throws UserMgtServiceException;

	List<Role> getRoles(String userId) throws UserMgtServiceException;

	boolean hasRole(String userId, String roleId) throws UserMgtServiceException;

	List<Permission> getPrivileges(String userId) throws UserMgtServiceException;

	boolean hasPrivilege(String userId, String privilege) throws UserMgtServiceException;
}
