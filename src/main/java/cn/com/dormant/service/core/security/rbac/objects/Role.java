package cn.com.dormant.service.core.security.rbac.objects;

import java.util.List;

public class Role {
	private Integer	id			= null;

	private String 	name		= "";
	
	private String  	remark		= "";
	
	private List<Permission> permissions = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the privileges
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the privileges to set
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
}
