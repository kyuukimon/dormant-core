package cn.com.dormant.service.core.security.rbac.services;

import cn.com.dormant.service.core.security.rbac.objects.OperationPermission;
import cn.com.dormant.service.core.security.rbac.objects.Permission;
import cn.com.dormant.service.core.security.rbac.objects.Role;

import java.util.List;


public interface IRoleMgtService<T extends Role, P extends Permission> {
	T createRole();
	
	boolean deleteRole(T role);
	
	boolean modifyRole(T role);
	
	List<T> getAllRoles();

	void addPermission(T role, P permission);

	void addPermission(T role, List<P> permissions);

	void removePermission(T role, P permission);

	void removePermission(T role, List<P> permissions);

	void addPermission(Integer roleId, P permission);

	void addPermission(Integer roleId, List<P> permissions);

	void removePermission(Integer roleId, P permission);

	void removePermission(Integer roleId, List<P> permissions);
}
