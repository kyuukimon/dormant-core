package cn.com.dormant.service.core.manager;

import cn.com.dormant.service.core.message.MessageException;
import cn.com.dormant.service.core.message.MessagePublisher;
import cn.com.dormant.service.core.security.SecurityManager;
import cn.com.dormant.service.core.security.SecurityUtil;
import cn.com.dormant.service.core.security.SysResourceType;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <code>DefaultDataManager<code>
 *
 * @description: This class is one default manager implementation for data feature related business
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/03/04
 * @version: 1.0
 */
public abstract class DefaultDataManager<T extends DataRecord<PK>,PK extends Serializable>
        extends BaseDataManager<T,PK> {
    private cn.com.dormant.service.core.security.SecurityManager securityManager;

    private List<ManagerListener> listeners = Collections.synchronizedList(new LinkedList<ManagerListener>());

    public DefaultDataManager() {
        super();
    }

    public DefaultDataManager(String id, String name) {
        super(id, name);
    }

    @Override
    public void afterLoad(List<T> list) throws ManagerException {
        if(list == null) {
            return ;
        }

        for(T record : list) {
            mask(record);
        }
    }

    @Override
    public void beforeLoad() throws ManagerException {
        boolean bRet = true;

        //1. Check if user has permission to list all records
        bRet = hasPermission(
                SecurityUtil.getPermissionStr(SysResourceType.MODULE.toString(),
                        new String[]{DataOperation.OPER_LOAD.getCode()},
                        getId()));
        if(!bRet) {
            throw new ManagerException("Have not permission to list records");
        }

    }

    @Override
    public void beforeGet(PK id) throws ManagerException {
        boolean bRet = true;

        //1. Check if user has permission to get one record
        bRet = hasPermission(
                SecurityUtil.getPermissionStr(SysResourceType.MODULE.toString(),
                        new String[]{DataOperation.OPER_DETAIL.getCode()},
                        getId()));
        if(!bRet) {
            throw new ManagerException("Have not permission to get record");
        }

    }

    @Override
    public void afterGet(T record) throws ManagerException {
        mask(record);
    }

    @Override
    public void beforeDelete(PK id) throws ManagerException {
        boolean bRet = true;

        //1. Check if user has permission to create one  record
        bRet = hasPermission(
                SecurityUtil.getPermissionStr(SysResourceType.MODULE.toString(),
                        new String[]{DataOperation.OPER_DELETE.getCode()},
                        getId()));
        if(!bRet) {
            throw new ManagerException("Have not permission to delete record: " + id);
        }

    }

    @Override
    public void beforePost(T record, Object... addition) throws ManagerException {
        boolean bRet = true;

        //1. Check if user has permission to update one  record
        bRet = hasPermission(
                SecurityUtil.getPermissionStr(SysResourceType.MODULE.toString(),
                        new String[]{DataOperation.OPER_POST.getCode()},
                        getId()));
        if(!bRet) {
            throw new ManagerException("Have not permission to update record: " +
                    record.getId());
        }
        //2. Validate record information
//        try {
        bRet = validate(record,addition);
        if (!bRet) {
            throw new ManagerException("Failed to validate record information: " +
                    record.getId());
        }
//        }catch (Exception e) {
//            throw new ManagerException(e.getMessage());
//        }

        //3. Unmask record information
        unMask(record);

    }

    @Override
    public void beforeCreate(Object... args) throws ManagerException {
        boolean bRet = true;

        //1. Check if user has permission to create one  record
        bRet = hasPermission(
                SecurityUtil.getPermissionStr(SysResourceType.MODULE.toString(),
                        new String[]{DataOperation.OPER_CREATE.getCode()},
                        getId()));
        if(!bRet) {
            throw new ManagerException("Have not permission to create record." );
        }
//        //2. Validate record information
//        bRet = validate(record);
//        if(!bRet) {
//            throw new ManagerException("Failed to validate record information: " +
//                    record.getId());
//        }
//
//        //3. Unmask record information
//        unMask(record);

    }

    @Override
    public void beforeExecute(String action, PK id, Map<String, Object> args) throws ManagerException {
        boolean bRet = true;

        //1. Check if user has permission to execute special operation
        bRet &= hasPermission(
                SecurityUtil.getPermissionStr(SysResourceType.MODULE.toString(),
                        new String[]{DataOperation.OPER_EXECUTE.getCode()},
                        getId()));
        if(!bRet) {
            throw new ManagerException("Have not permission to execute action: " + action);
        }

    }

    @Override
    public void afterPost(T record, Object... addition) throws ManagerException {
        if(record == null) {
            throw new ManagerException("Data record is null");
        }

        notifyPosted(record);

        if(isMessagePublishSupport()) {
            publishMessage(DataOperation.OPER_POST.getCode(), record);
        }

        finishHandle(record, addition);
    }

    public void finishHandle(T record, Object... addition) throws ManagerException {

    }

    @Override
    public void afterDelete(PK id) throws ManagerException {
        if(id == null) {
            throw new ManagerException("Data record is null");
        }

        notifyDeleted(id);

        if(isMessagePublishSupport()) {
            //Object deletedRes = drawDeleteRelatedData();
            publishMessage(DataOperation.OPER_DELETE.getCode(), id);
        }

    }

    @Override
    public void afterCreate(T record) throws ManagerException {
        if(record == null) {
            throw new ManagerException("Data record is null");
        }

        notifyCreated(record);

        if(isMessagePublishSupport()) {
            publishMessage(DataOperation.OPER_CREATE.getCode(), record);
        }

    }

    @Override
    public void afterExecute(String action, PK id, Map<String, Object> args) throws ManagerException {
        notifyOperated(action, id);

    }

    @Override
    public void beforeUpdate(T record, Object... addition) throws ManagerException {
        boolean bRet = true;

        //1. Check if user has permission to update one  record
        bRet = hasPermission(
                SecurityUtil.getPermissionStr(SysResourceType.MODULE.toString(),
                        new String[]{DataOperation.OPER_UPDATE.getCode()},
                        getId()));
        if(!bRet) {
            throw new ManagerException("Have not permission to update record: " +
                    record.getId());
        }
        //2. Validate record information
//        try {
        bRet = validateUpdate(record,addition);
        if (!bRet) {
            throw new ManagerException("Failed to validate updated record: " +
                    record.getId());
        }
//        }catch (Exception e) {
//            throw new ManagerException(e.getMessage());
//        }

        //3. Unmask record information
        unMask(record);

    }

    @Override
    public void afterUpdate(T record, Object... addition) throws ManagerException {
        if(record == null) {
            throw new ManagerException("Data record is null");
        }

        notifyUpdated(record);

        if(isMessagePublishSupport()) {
            publishMessage(DataOperation.OPER_UPDATE.getCode(), record);
        }

    }

    @Override
    public void doUpdate(T record, Object... addition) throws ManagerException {

    }

    /**
     * Validate if record information are legal data
     *
     * @param record
     * @return
     */
    public boolean validateUpdate(T record, Object... addition) throws ManagerException {
        return false;
    }

    /**
     * Validate if record information are legal data
     *
     * @param record
     * @return
     */
    public abstract boolean validate(T record, Object... addition) throws ManagerException;

    /**
     * Check if has operation permission
     *
     * @return
     */
    public boolean hasPermission(String permission){
        //About security handle, put it forward to API handler layer
        //So here just to return true

        boolean debug = true;
        if(debug) {
            return true;
        }
        if(securityManager != null) {
            return securityManager.isPermitted(permission);
        }
        return false;
    }

    public abstract T mask(T record);

    public abstract T unMask(T record);

    public void publishMessage(String operation, Object obj){
        String topicName = "";//MsgUtil.getTopicNameByResourceType(getId());

        try {
            MessagePublisher.publish(topicName, operation, obj);
        }catch (MessageException e) {
            e.printStackTrace();
        }
    }

    protected boolean isMessagePublishSupport() {
        return false;
    }

    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }


    public void registerListener(ManagerListener listener) {
        this.listeners.add(listener);
    }

    public void unregisterListener(ManagerListener listener) {
        this.listeners.remove(listener);
    }

    public void notifyPosted(T record) {
//        DataRecord record = new DataRecord();
//        record.setId(id);
        ManagerEvent event = new ManagerEvent(record);
        for(ManagerListener listener : listeners) {
            listener.onPosted(event);
        }
    }

    public void notifyUpdated(T record) {
//        DataRecord record = new DataRecord();
//        record.setId(id);
        ManagerEvent event = new ManagerEvent(record);
        for(ManagerListener listener : listeners) {
            listener.onUpdated(event);
        }
    }

    public void notifyCreated(T record) {

        ManagerEvent event = new ManagerEvent(record);
        for(ManagerListener listener : listeners) {
            listener.onCreated(event);
        }
    }

    public void notifyDeleted(PK id) {
        DataRecord record = new DataRecord();
        record.setId(id);
        ManagerEvent event = new ManagerEvent(record);
        for(ManagerListener listener : listeners) {
            listener.onDeleted(event);
        }
    }

    public void notifyOperated(String operation, PK id) {
        DataRecord record = new DataRecord();
        record.setId(id);
        ManagerEvent event = new ManagerEvent(record);
        for(ManagerListener listener : listeners) {
            listener.onOperated(event, operation);
        }
    }
}
