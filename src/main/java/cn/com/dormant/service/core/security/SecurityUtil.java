/*****************************************************************************
 * HISTORY
 *
 * 2014/09/18   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security;

/**
 * <code>SecurityUtil<code>
 *
 * @description: This is one common class for security related methods such as permission string parser and so on
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/18
 * @version: 1.0
 */
public class SecurityUtil {
    /**
     * Get resource type from permission string
     * Permission string format: [resource type]:[operation1,operation2,...]:[resource instance]
     *
     * @param permission
     * @return
     */
    public static String getResourceType(String permission) {
        String retStr = null;

        if(permission == null || permission.trim().isEmpty()) {
            return retStr;
        }

        String[] strs = permission.split(":");
        if(strs.length < 3) {
            //It is one invalid permission string
            return retStr;
        }

        retStr = strs[0];
        return retStr;
    }

    /**
     * Get all permitted operations on resource instance from permission string
     * Permission string format: [resource type]:[operation1,operation2,...]:[resource instance]
     *
     * @param permission
     * @return
     */
    public static String[] getOperations(String permission) {
        String[] operations = null;

        if(permission == null || permission.trim().isEmpty()) {
            return operations;
        }

        String[] strs = permission.split(":");
        if(strs.length < 3) {
            //It is one invalid permission string
            return operations;
        }

        if(strs[1].trim().isEmpty()) {
            return operations;
        }

        operations = strs[1].split(",");
        return operations;
    }

    /**
     * Get resource instance from permission string
     * Permission string format: [resource type]:[operation1,operation2,...]:[resource instance]
     * If resource instance string is *, then it represent these operations is permitted on all instance of
     * special resource type
     *
     * @param permission
     * @return
     */
    public static String getResourceInstance(String permission) {
        String retStr = null;

        if(permission == null || permission.trim().isEmpty()) {
            return retStr;
        }

        String[] strs = permission.split(":");
        if(strs.length < 3) {
            //It is one invalid permission string
            return retStr;
        }

        retStr = strs[2];
        return retStr;
    }

    /**
     * Generate permission string
     *
     * @param resourceType
     * @param operations
     * @param resourceInstance
     * @return
     */
    public static String getPermissionStr(String resourceType, String[] operations, String resourceInstance) {
        if(resourceType == null || resourceType.trim().isEmpty()) {
            return null;
        }

        if(operations == null || operations.length == 0) {
            return null;
        }

        if(resourceInstance == null || resourceInstance.trim().isEmpty()) {
            return null;
        }

        String operStr = "";
        for(String oper : operations) {
            if(oper == null || oper.trim().isEmpty()) {
                continue;
            }

            operStr = operStr.isEmpty() ? oper : operStr + "," + oper;
        }

        return (resourceType+":"+operStr+":"+resourceInstance);
    }

}
