package cn.com.dormant.service.core.security.rbac.objects;


public class OperationPermission extends ResourcePermission {
    private String		operation		= null;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
