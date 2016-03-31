package cn.com.dormant.service.core.security.rbac.objects;

/**
 * <code>AccountSettingData</code> class manage the desired configuration for an account.
 * 
 * @creation 2011-07-27
 * @author 	 Gavin Wang(aguang_1@126.com)
 * 
 *  <TABLE>
 *      <TR><TD>DATE</TD><TD>Defect</TD><TD>who</TD><TD>Change Description</TD></TR>
 *      <TR><TD>2011-07-27</TD><TD>None</TD><TD>Gavin Wang(aguang_1@126.com)</TD><TD>Initial create</TD></TR>
 *  </TABLE>
 */
public class AccountSettingData {
	/*
	 * Indicates the number of previous passwords that shall be maintained for the account. 
	 * The account shall preclude the selection of a password if it occurs in the password history. 
	 * A value of zero shall indicate that a password history is not maintained.
	 */
	private int 	passwordHistoryDepth 			= 0;
	/*
	 * Indicates the maximum password age enforced for the account. 
	 * The value shall be expressed in interval format. A value of 0 shall indicate that 
	 * the password aging is not enforced.
	 */
	private int 	passwordExpired					= 0;
	/*
	 * Indicates the rules for constructing a complex password enforced by the Account.
	 * Minimum Length - a minimum length is enforced for passwords for the account.
	 * Preclude User ID - inclusion precluding the password from including the user ID is supported. 
	 * Maximum Repeating Characters - a limit will be enforced on the number of times a character can occur consecutively. 
	 * Lower Case Alpha - at least one lower case alpha character is required. 
	 * Upper Case Alpha - at least one upper case alpha character is required. 
	 * Numeric Character - at least one numeric character is required. 
	 * Special Character - at least one special character is required.
	 */
	private boolean complexPasswordRulesEnforced	= false;
	/*
	 * Specifies the interval after which if an account has been inactive, it shall be Disabled. 
	 * The value shall be expressed in interval format. A value of 0 shall indicate that the account 
	 * will not be disabled due to inactivity.
	 */
	private int		inactivityTimeout				= 0;
	/*
	 * Indicates the number of successive failed login attempts that shall result in the Account 
	 * being disabled. A value of zero shall indicate that the Account will not be disabled due 
	 * to successive failed login attempts.
	 */
	private int		succLoginFailures				= 0;
	/**
	 * @return the passwordHistoryDepth
	 */
	public int getPasswordHistoryDepth() {
		return passwordHistoryDepth;
	}
	/**
	 * @param passwordHistoryDepth the passwordHistoryDepth to set
	 */
	public void setPasswordHistoryDepth(int passwordHistoryDepth) {
		this.passwordHistoryDepth = passwordHistoryDepth;
	}
	/**
	 * @return the passwordExpired
	 */
	public int getPasswordExpired() {
		return passwordExpired;
	}
	/**
	 * @param passwordExpired the passwordExpired to set
	 */
	public void setPasswordExpired(int passwordExpired) {
		this.passwordExpired = passwordExpired;
	}
	/**
	 * @return the complexPasswordRulesEnforced
	 */
	public boolean isComplexPasswordRulesEnforced() {
		return complexPasswordRulesEnforced;
	}
	/**
	 * @param complexPasswordRulesEnforced the complexPasswordRulesEnforced to set
	 */
	public void setComplexPasswordRulesEnforced(boolean complexPasswordRulesEnforced) {
		this.complexPasswordRulesEnforced = complexPasswordRulesEnforced;
	}
	/**
	 * @return the inactivityTimeout
	 */
	public int getInactivityTimeout() {
		return inactivityTimeout;
	}
	/**
	 * @param inactivityTimeout the inactivityTimeout to set
	 */
	public void setInactivityTimeout(int inactivityTimeout) {
		this.inactivityTimeout = inactivityTimeout;
	}
	/**
	 * @return the succLoginFailures
	 */
	public int getSuccLoginFailures() {
		return succLoginFailures;
	}
	/**
	 * @param succLoginFailures the succLoginFailures to set
	 */
	public void setSuccLoginFailures(int succLoginFailures) {
		this.succLoginFailures = succLoginFailures;
	}
		
}
