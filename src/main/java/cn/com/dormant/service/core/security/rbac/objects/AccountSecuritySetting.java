package cn.com.dormant.service.core.security.rbac.objects;

public class AccountSecuritySetting {
	
	/*
	 * High security setting 
	 */
	public final static int SECURITY_LEVEL_HIGH = 1;
	
	/*
	 * Legacy security setting 
	 */
	public final static int SECURITY_LEVEL_LEGACY = 2;
	
	/*
	 * Custom security setting
	 */
	public final static int SECURITY_LEVEL_CUSTOM = 3;
	
	/*
	 * Account security level
	 */
	private int 		securityLevel = SECURITY_LEVEL_HIGH;
	
    private boolean     passwordRequired;
    
    /*
     * Password expiration period (days)
     */
    private int         passwordExpiration;
    
    private int         minPwdLength;
    
    /*
     * Minimum password reuse cycle
     */
    private int         minPwdReuseCycle;
    
    /*
     * Minimum password change interval (hours)
     */
    private int	        minPwdChangeInteval;
    
    /*
     * Maximum number of login failures (times)
     */
    private int	        maxNumOfLoginFailures;
    
    /*
     * Lockout period after maximum login failures (minutes)
     */
    private int		    lockoutPeriod;
    
    /*
     * Complex password rules
     */
    private boolean     complexPwdRules;
    
    /*
     * Minimum different characters in passwords
     */
    private int		    minNumOfUniqueCharInPwd;
    
    /*
     * Force user to change password on first access
     */
    private boolean     changePwdOnFirstAccess;
    
    /*
     * Factory default 'USERID' account password must be changed on next login
     */
    private boolean	 	changeDftAccOnNextLogin;
    
    /*
     * Inactivity alert period (days)
     */
    private int   	 	inactivAlertPeriod;
    
    /*
     * Inactivity alert and disable period (days)
     */
    private int     	inactivAlertDisablPeriod;

	/**
	 * @return the securityLevel
	 */
	public int getSecurityLevel() {
		return securityLevel;
	}

	/**
	 * @param securityLevel the securityLevel to set
	 */
	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}

	/**
	 * @return the passwordRequired
	 */
	public boolean isPasswordRequired() {
		return passwordRequired;
	}

	/**
	 * @param passwordRequired the passwordRequired to set
	 */
	public void setPasswordRequired(boolean passwordRequired) {
		this.passwordRequired = passwordRequired;
	}

	/**
	 * @return the passwordExpiration
	 */
	public int getPasswordExpiration() {
		return passwordExpiration;
	}

	/**
	 * @param passwordExpiration the passwordExpiration to set
	 */
	public void setPasswordExpiration(int passwordExpiration) {
		this.passwordExpiration = passwordExpiration;
	}

	/**
	 * @return the minPwdLength
	 */
	public int getMinPwdLength() {
		return minPwdLength;
	}

	/**
	 * @param minPwdLength the minPwdLength to set
	 */
	public void setMinPwdLength(int minPwdLength) {
		this.minPwdLength = minPwdLength;
	}

	/**
	 * @return the minPwdReuseCycle
	 */
	public int getMinPwdReuseCycle() {
		return minPwdReuseCycle;
	}

	/**
	 * @param minPwdReuseCycle the minPwdReuseCycle to set
	 */
	public void setMinPwdReuseCycle(int minPwdReuseCycle) {
		this.minPwdReuseCycle = minPwdReuseCycle;
	}

	/**
	 * @return the minPwdChangeInteval
	 */
	public int getMinPwdChangeInteval() {
		return minPwdChangeInteval;
	}

	/**
	 * @param minPwdChangeInteval the minPwdChangeInteval to set
	 */
	public void setMinPwdChangeInteval(int minPwdChangeInteval) {
		this.minPwdChangeInteval = minPwdChangeInteval;
	}

	/**
	 * @return the maxNumOfLoginFailures
	 */
	public int getMaxNumOfLoginFailures() {
		return maxNumOfLoginFailures;
	}

	/**
	 * @param maxNumOfLoginFailures the maxNumOfLoginFailures to set
	 */
	public void setMaxNumOfLoginFailures(int maxNumOfLoginFailures) {
		this.maxNumOfLoginFailures = maxNumOfLoginFailures;
	}

	/**
	 * @return the lockoutPeriod
	 */
	public int getLockoutPeriod() {
		return lockoutPeriod;
	}

	/**
	 * @param lockoutPeriod the lockoutPeriod to set
	 */
	public void setLockoutPeriod(int lockoutPeriod) {
		this.lockoutPeriod = lockoutPeriod;
	}

	/**
	 * @return the complexPwdRules
	 */
	public boolean isComplexPwdRules() {
		return complexPwdRules;
	}

	/**
	 * @param complexPwdRules the complexPwdRules to set
	 */
	public void setComplexPwdRules(boolean complexPwdRules) {
		this.complexPwdRules = complexPwdRules;
	}

	/**
	 * @return the minNumOfUniqueCharInPwd
	 */
	public int getMinNumOfUniqueCharInPwd() {
		return minNumOfUniqueCharInPwd;
	}

	/**
	 * @param minNumOfUniqueCharInPwd the minNumOfUniqueCharInPwd to set
	 */
	public void setMinNumOfUniqueCharInPwd(int minNumOfUniqueCharInPwd) {
		this.minNumOfUniqueCharInPwd = minNumOfUniqueCharInPwd;
	}

	/**
	 * @return the changePwdOnFirstAccess
	 */
	public boolean isChangePwdOnFirstAccess() {
		return changePwdOnFirstAccess;
	}

	/**
	 * @param changePwdOnFirstAccess the changePwdOnFirstAccess to set
	 */
	public void setChangePwdOnFirstAccess(boolean changePwdOnFirstAccess) {
		this.changePwdOnFirstAccess = changePwdOnFirstAccess;
	}

	/**
	 * @return the changeDftAccOnNextLogin
	 */
	public boolean isChangeDftAccOnNextLogin() {
		return changeDftAccOnNextLogin;
	}

	/**
	 * @param changeDftAccOnNextLogin the changeDftAccOnNextLogin to set
	 */
	public void setChangeDftAccOnNextLogin(boolean changeDftAccOnNextLogin) {
		this.changeDftAccOnNextLogin = changeDftAccOnNextLogin;
	}

	/**
	 * @return the inactivAlertPeriod
	 */
	public int getInactivAlertPeriod() {
		return inactivAlertPeriod;
	}

	/**
	 * @param inactivAlertPeriod the inactivAlertPeriod to set
	 */
	public void setInactivAlertPeriod(int inactivAlertPeriod) {
		this.inactivAlertPeriod = inactivAlertPeriod;
	}

	/**
	 * @return the inactivAlertDisablPeriod
	 */
	public int getInactivAlertDisablPeriod() {
		return inactivAlertDisablPeriod;
	}

	/**
	 * @param inactivAlertDisablPeriod the inactivAlertDisablPeriod to set
	 */
	public void setInactivAlertDisablPeriod(int inactivAlertDisablPeriod) {
		this.inactivAlertDisablPeriod = inactivAlertDisablPeriod;
	}
	
	
}
