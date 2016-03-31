package cn.com.dormant.service.core.manager;

import java.util.Map;

/**
 * <code>TransactionHelper<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/7/15
 * @version: 1.0
 */
public interface TransactionHelper<T,M,PK> {
    T saveInTransaction(DataSubmitter submitter, M curMaster, Object... details);

    T updateInTransaction(DataSubmitter submitter, M curMaster, Object... details);

    T deleteInTransaction(DataSubmitter submitter, PK curMasterId);

    Object executeInTransaction(DataSubmitter submitter, String operation, PK id, Map<String, Object> args);
}
