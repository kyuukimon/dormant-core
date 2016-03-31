package cn.com.dormant.service.core.security.rbac.objects.extended;

import cn.com.dormant.service.core.security.rbac.objects.Group;

/**
 * Created by lenovo on 2015/3/11.
 */
public class DefaultGroup extends Group {

    private String  created     = null;

    private String  createdBy   = null;

    private String  updated     = null;

    private String  updatedBy   = null;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
