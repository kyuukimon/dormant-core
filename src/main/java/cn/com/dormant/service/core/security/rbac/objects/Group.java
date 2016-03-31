package cn.com.dormant.service.core.security.rbac.objects;

import java.util.List;

public class Group {
	/*
	 * Group ID
	 */
	private String id = "";
	
	/*
	 * Group description
	 */
	private String remark = "";
	
	/*
	 * Group roles
	 */
	private List<Role> roles = null;

	/**
	 * Users contained in group
	 */
	private List<UserAccount> users	= null;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public List<UserAccount> getUsers() {
		return users;
	}

	public void setUsers(List<UserAccount> users) {
		this.users = users;
	}
}
