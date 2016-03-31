package cn.com.dormant.service.core.manager;

import cn.com.dormant.service.core.common.Pager;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <code>DataManager<code>
 *
 * @description: This class is one base interface providing data related operations such as list,create,update,delete
 * and so on
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/08/19
 * @version: 1.0
 */
public interface DataManager<T extends DataRecord<PK>,PK extends Serializable> {

    /**
     * Load data set by page. If pager param is null, then load all data set
     *
     * If pager is null , maybe load all data set without page
     *
     * @return
     * @throws ManagerException
     */
    List<T> load(Pager pager) throws ManagerException;

    /**
     * Get one record by its id
     *
     * @param id
     * @return
     * @throws ManagerException
     */
    T get(PK id) throws ManagerException;

    /**
     * Delete one record by its id. If the record is not existed, then should throw one manager exception
     *
     * @param id
     * @throws ManagerException
     */
    void delete(PK id) throws ManagerException;

    /**
     * Batch delete records by its ids. If some record in collection is not existed,
     * then should throw one manager exception
     *
     * @param ids
     * @throws ManagerException
     */
    void delete(Collection<PK> ids) throws ManagerException;

    /**
     * Submit one record including save or update operation
     *
     * @param record
     * @throws ManagerException
     */
    void post(T record, Object... addition) throws ManagerException;

    /**
     * Update one record
     *
     * @param record
     * @throws ManagerException
     */
    void update(T record, Object... addition) throws ManagerException;

    /**
     * Create one new record and just initialize it, it is noted that the new record in non-persistent state here
     * and not to submit into storage such as db and so on
     *
     * @param
     * @return T
     * @throws ManagerException
     */
    T create(Object... args) throws ManagerException;

    /**
     * Execute some operation on one special record
     *
     * @param operation
     * @param id
     * @param args
     * @throws ManagerException
     */
    Object execute(String operation, PK id, Map<String, Object> args) throws ManagerException;


}
