package cn.com.dormant.service.core.security.rbac.objects;


import cn.com.dormant.service.core.security.rbac.objects.Permission;

public class ResourcePermission extends Permission {
    private Integer	resourceType	= null;

    private String		resourceId		= null;

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
