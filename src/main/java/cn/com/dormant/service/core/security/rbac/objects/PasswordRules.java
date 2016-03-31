package cn.com.dormant.service.core.security.rbac.objects;

public class PasswordRules {
	/*
	 * Must be 8-32 characters
	 */
	private boolean rule01 = false;
	
	/*
	 * Must contain at least one number
	 */
	private boolean rule02 = false;
	
	/*
	 * Must contain at least one letter
	 */
	private boolean rule03 = false;
	
	/*
	 * Cannot contain white space characters
	 */
	private boolean rule04 = false;
	
	/*
	 * Cannot be a repeat or reverse of user name
	 */
	private boolean rule05 = false;
	
	/*
	 * No more than 3 of the same characters used consecutively
	 */
	private boolean rule06 = false;
	
	/*
	 * Must contain at least 2 of the following combinations:
	 * select from rule08, rule09, rule10
	 */
	private boolean rule07 = false;
	
	/*
	 * At least one upper case letter
	 */
	private boolean rule08 = false;
	
	/*
	 * At least one lower case letter
	 */
	private boolean rule09 = false;
	
	/*
	 * At least one special character
	 */
	private boolean rule10 = false;
	
	/*
	 * Password and password confirm values must match
	 */
	private boolean rule11 = false;

	/**
	 * @return the rule01
	 */
	public boolean isRule01() {
		return rule01;
	}

	/**
	 * @param rule01 the rule01 to set
	 */
	public void setRule01(boolean rule01) {
		this.rule01 = rule01;
	}

	/**
	 * @return the rule02
	 */
	public boolean isRule02() {
		return rule02;
	}

	/**
	 * @param rule02 the rule02 to set
	 */
	public void setRule02(boolean rule02) {
		this.rule02 = rule02;
	}

	/**
	 * @return the rule03
	 */
	public boolean isRule03() {
		return rule03;
	}

	/**
	 * @param rule03 the rule03 to set
	 */
	public void setRule03(boolean rule03) {
		this.rule03 = rule03;
	}

	/**
	 * @return the rule04
	 */
	public boolean isRule04() {
		return rule04;
	}

	/**
	 * @param rule04 the rule04 to set
	 */
	public void setRule04(boolean rule04) {
		this.rule04 = rule04;
	}

	/**
	 * @return the rule05
	 */
	public boolean isRule05() {
		return rule05;
	}

	/**
	 * @param rule05 the rule05 to set
	 */
	public void setRule05(boolean rule05) {
		this.rule05 = rule05;
	}

	/**
	 * @return the rule06
	 */
	public boolean isRule06() {
		return rule06;
	}

	/**
	 * @param rule06 the rule06 to set
	 */
	public void setRule06(boolean rule06) {
		this.rule06 = rule06;
	}

	/**
	 * @return the rule07
	 */
	public boolean isRule07() {
		return rule07;
	}

	/**
	 * @param rule07 the rule07 to set
	 */
	public void setRule07(boolean rule07) {
		this.rule07 = rule07;
	}

	/**
	 * @return the rule08
	 */
	public boolean isRule08() {
		return rule08;
	}

	/**
	 * @param rule08 the rule08 to set
	 */
	public void setRule08(boolean rule08) {
		this.rule08 = rule08;
	}

	/**
	 * @return the rule09
	 */
	public boolean isRule09() {
		return rule09;
	}

	/**
	 * @param rule09 the rule09 to set
	 */
	public void setRule09(boolean rule09) {
		this.rule09 = rule09;
	}

	/**
	 * @return the rule10
	 */
	public boolean isRule10() {
		return rule10;
	}

	/**
	 * @param rule10 the rule10 to set
	 */
	public void setRule10(boolean rule10) {
		this.rule10 = rule10;
	}

	/**
	 * @return the rule11
	 */
	public boolean isRule11() {
		return rule11;
	}

	/**
	 * @param rule11 the rule11 to set
	 */
	public void setRule11(boolean rule11) {
		this.rule11 = rule11;
	}
	
	
}
