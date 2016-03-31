package cn.com.dormant.service.core.manager;

import java.util.Map;

/**
 * <code>DataSubmitter<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/7/15
 * @version: 1.0
 */
public interface DataSubmitter<M, PK> {
    boolean submitSave(M curMaster, Object... details) throws ManagerException;

    void submitDelete(PK curMasterId) throws ManagerException;

    boolean submitUpdate(M curMaster, Object... details) throws ManagerException;

    Object submitExecute(String operation, PK id, Map<String, Object> args) throws ManagerException;
}
