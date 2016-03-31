package cn.com.dormant.service.core.security.rbac.objects;

public class GlobalLoginSetting {
	/*
	 * Local authentication only
	 */
	public final static int LOCAL_AUTHEN_ONLY = 1;
	
	/*
	 * External authentication server only. 
	 * This authentication method requires an active LDAP server
	 */
	public final static int EXT_AUTHEN_SERVER_ONLY = 2;
	
	/*
	 * Local first, then external authentication server
	 * This authentication method need to configure an active LDAP server
	 */
	public final static int LOCAL_FIRST_THEN_AUTHEN_SERVER = 3;
	
	/*
	 * External authentication server first, then local authentication
	 * This authentication method need to configure an active LDAP server
	 */
	public final static int AUTHEN_SERVER_FIRST_THEN_LOCAL = 4;
	
	/*
	 * User authentication method
	 */
	private int userAuthenticationMethod = LOCAL_AUTHEN_ONLY;
	
	/*
	 * Session timeout for one account(Unit: minute) 
	 * 0 - No timeout
	 */
	private int sessionTimeout = 0;
	
	/*
	 * Number of simultaneous active sessions for LDAP users
	 */
	private int numOfSimuSessions = 0;
	
	/*
	 * Account security setting
	 */
	private AccountSecuritySetting securitySetting = null;

	/**
	 * @return the userAuthenticationMethod
	 */
	public int getUserAuthenticationMethod() {
		return userAuthenticationMethod;
	}

	/**
	 * @param userAuthenticationMethod the userAuthenticationMethod to set
	 */
	public void setUserAuthenticationMethod(int userAuthenticationMethod) {
		this.userAuthenticationMethod = userAuthenticationMethod;
	}

	/**
	 * @return the sessionTimeout
	 */
	public int getSessionTimeout() {
		return sessionTimeout;
	}

	/**
	 * @param sessionTimeout the sessionTimeout to set
	 */
	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	/**
	 * @return the numOfSimuSessions
	 */
	public int getNumOfSimuSessions() {
		return numOfSimuSessions;
	}

	/**
	 * @param numOfSimuSessions the numOfSimuSessions to set
	 */
	public void setNumOfSimuSessions(int numOfSimuSessions) {
		this.numOfSimuSessions = numOfSimuSessions;
	}

	/**
	 * @return the securitySetting
	 */
	public AccountSecuritySetting getSecuritySetting() {
		return securitySetting;
	}

	/**
	 * @param securitySetting the securitySetting to set
	 */
	public void setSecuritySetting(AccountSecuritySetting securitySetting) {
		this.securitySetting = securitySetting;
	}
	
	
}
