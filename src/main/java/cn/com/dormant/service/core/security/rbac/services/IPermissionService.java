package cn.com.dormant.service.core.security.rbac.services;

import cn.com.dormant.service.core.security.rbac.objects.OperationPermission;
import cn.com.dormant.service.core.security.rbac.objects.Permission;

import java.util.List;

public interface IPermissionService<T extends Permission> {
    List<T> getAvailablePermissions(Integer resourceType, String resourceId);


}
