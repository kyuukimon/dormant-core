package cn.com.dormant.service.core.security.rbac.objects.extended;

import cn.com.dormant.service.core.security.rbac.objects.AccountSettingData;
import cn.com.dormant.service.core.security.rbac.objects.UserAccount;

/**
 * <code>DefaultUserAccount</code> class provide an default implemention of the <code>Account</code>.
 * 
 * @creation 2011-07-27
 * @author 	 Gavin Wang(aguang_1@126.com)
 * 
 *  <TABLE>
 *      <TR><TD>DATE</TD><TD>Defect</TD><TD>who</TD><TD>Change Description</TD></TR>
 *      <TR><TD>2011-07-27</TD><TD>None</TD><TD>Gavin Wang(aguang_1@126.com)</TD><TD>Initial create</TD></TR>
 *  </TABLE>
 */
public class DefaultUserAccount extends UserAccount {
	/*
	 * Indicates the account has never been used.
	 */
	public final static String 	LAST_LOGIN_NEVER_USED	= "00000000000000";
	/*
	 * Indicates that the contents of account password are not encrypted.
	 */
	public final static int		ENCRYP_ALGORITHM_NONE	= 0;
	/*
	 * Indicates that the contents of account password are encrypted using an algorithm 
	 * not specifically identified.
	 */
	public final static int		ENCRYP_ALGORITHM_OTHER	= 1;
	/*
	 * Indicates that the contents of account password are encrypted using an MD5 algorithm.
	 */
	public final static int		ENCRYP_ALGORITHM_MD5	= 2;
	/*
	 * The remark property provides a textual description of the account.
	 */
	private String	remark		= "";
	/*
	 * When the object was installed. 
	 * Lack of a value does not indicate that the object is not installed.
	 * 
	 * Value format: YYYYMMDDHHMMSS 
	 * Value length: 14 
	 */
	private String 	installedDate	= "";
	/*
	 * LastLogin shall be an absolute date-time that specifies the last successful 
	 * authentication that occurred for this Account.
	 * A value of 00000000000000 shall indicate the Account has never been used. 
	 * A value of NULL or blank shall indicate the last successful login is unknown.
	 */
	private String 	lastLogin		= LAST_LOGIN_NEVER_USED;
	/*
	 * The encryption algorithm (if any) used by the client to produce the value in the password property 
	 * when creating or modifying an account. 
	 * The original password is encrypted using the algorithm specified in this property, and password
	 * contains the resulting encrypted value. 
	 */
	private int		passwordEncryptionAlgorithm		= 0;
	/*
	 * This property manage the desired configuration for an account
	 */
	private AccountSettingData accountSettingData	= new AccountSettingData();

	/*
	 * Indicates the account password.
	 */
	private String 	password	= "";

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the installedDate
	 */
	public String getInstalledDate() {
		return installedDate;
	}
	/**
	 * @param installedDate the installedDate to set
	 */
	public void setInstalledDate(String installedDate) {
		this.installedDate = installedDate;
	}
	/**
	 * @return the lastLogin
	 */
	public String getLastLogin() {
		return lastLogin;
	}
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	/**
	 * @return the passwordEncryptionAlgorithm
	 */
	public int getPasswordEncryptionAlgorithm() {
		return passwordEncryptionAlgorithm;
	}
	/**
	 * @param passwordEncryptionAlgorithm the passwordEncryptionAlgorithm to set
	 */
	public void setPasswordEncryptionAlgorithm(int passwordEncryptionAlgorithm) {
		this.passwordEncryptionAlgorithm = passwordEncryptionAlgorithm;
	}
	/**
	 * @return the accountSettingData
	 */
	public AccountSettingData getAccountSettingData() {
		return accountSettingData;
	}
	/**
	 * @param accountSettingData the accountSettingData to set
	 */
	public void setAccountSettingData(AccountSettingData accountSettingData) {
		this.accountSettingData = accountSettingData;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
