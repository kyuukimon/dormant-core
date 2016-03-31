package cn.com.dormant.service.core.security.rbac.objects;

import java.util.List;

/**
 * <code>Account</code> class provide an interface to locally stored authentication 
 * information. This class does not provide accounting information such as: a history when a user was 
 * logged into a system; or billing information.
 * 
 * @creation 2011-07-27
 * @author 	 Gavin Wang(aguang_1@126.com)
 * 
 *  <TABLE>
 *      <TR><TD>DATE</TD><TD>Defect</TD><TD>who</TD><TD>Change Description</TD></TR>
 *      <TR><TD>2011-07-27</TD><TD>None</TD><TD>Gavin Wang(aguang_1@126.com)</TD><TD>Initial create</TD></TR>
 *  </TABLE>
 */
public class UserAccount {
	/*
	 * Enabled (2) indicates that the account is or could be executing commands, 
	 * will process any queued commands, and queues new requests.
	 * The account is properly configured and is enabled for use. An attempt to authenticate against
	 * the credentials of the account will be processed.
	 */
	public final static int ACCOUNT_STATE_ENABLED 		= 2;
	/*
	 * Disabled (3) indicates that the account will not execute commands and will drop any new requests.
	 * The account is disabled for use and attempts to authenticate against the credentials of the account
	 * will not be processed. 
	 * After the account has transitioned to 3(Disabled), the account may not be properly configured.
	 * The account may be properly configured but not required to be. Thus a transition to 2(Enabled) may
	 * not succeed without a reconfiguration of the account.
	 */
	public final static int ACCOUNT_STATE_DISABLED		= 3;
	/*
	 * Shutting Down (4) indicates that the account is in the process of going to a Disabled state.
	 */
	public final static int ACCOUNT_STATE_SHUTTING_DOWN	= 4;
	/*
	 * Not Applicable (5) indicates the account does not support being enabled or disabled.
	 */
	public final static int ACCOUNT_STATE_NOT_APPLICABLE= 5;
	/*
	 * Enabled but Offline (6) indicates that the account might be completing commands, 
	 * and will drop any new requests. 
	 * The account is properly configured but is not enabled for use. An attempt to authenticate against
	 * the credentials of the account will not be processed. A transition back to 2(Enabled) should succeed
	 * without requiring configuration of the account.
	 */
	public final static int ACCOUNT_STATE_OFFLINE		= 6;
	/*
	 * Test (7) indicates that the account is in a test state. 
	 */
	public final static int ACCOUNT_STATE_TEST			= 7;
	/*
	 * Deferred (8) indicates that the account might be completing commands, but will queue any new requests.
	 */
	public final static int ACCOUNT_STATE_DEFERRED		= 8;
	/*
	 * Quiesce (9) indicates that the account is enabled but in a restricted mode.
	 * The account is in a locked out state and requires corrective action to restore it to operational usage.
	 * 
	 */
	public final static int ACCOUNT_STATE_QUIESCE		= 9;
	/*
	 * Starting (10) indicates that the account is in the process of going to an Enabled state. 
	 * New requests are queued.
	 */
	public final static int ACCOUNT_STATE_STARTING		= 10;
	/*
	 * id represent identity.
	 * For an authentication service, the UserID may be the name of the user, 
	 * or for an authorization service the value which serves as a handle to a mapping of the identity.
	 */
	private String 	id			= "";
//	/*
//	 * Indicates the account password.
//	 */
//	private String 	password	= "";
	/*
	 *  Indicates the enabled and disabled states of an account
	 */
	private Integer	state		= ACCOUNT_STATE_DISABLED;
	
	/*
	 * Account roles
	 */
	private List<Role> roles = null;

	
	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAccount(String id) {
		super();
		this.id = id;
	}

	public UserAccount(String id,  Integer state) {
		super();
		this.id = id;
//		this.password = password;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
