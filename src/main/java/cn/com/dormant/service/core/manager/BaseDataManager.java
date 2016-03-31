package cn.com.dormant.service.core.manager;

import cn.com.dormant.service.core.common.Pager;
import cn.com.dormant.service.core.misc.CommonUtils;

import java.io.Serializable;
import java.util.*;

/**
 * <code>BaseDataManager<code>
 *
 * @description: This class is one base special manager for data feature related business
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/03/03
 * @version: 1.0
 */
public abstract class BaseDataManager<T extends DataRecord<PK>,PK extends Serializable>
        extends BaseManager implements DataManager<T,PK> {

    protected final static String MGT_KEY_DELETED_DATA = "deleted_data";

    protected final static String MGT_KEY_POSTED_DATA = "posted_data";

    protected final static String MGT_KEY_CREATED_DATA = "created_data";

    private ThreadLocal<Map<String, Object>> localCache = new ThreadLocal<Map<String,Object>>(){
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>();
        }
    };

    protected BaseDataManager() {
        super();
    }

    protected BaseDataManager(String id, String name) {
        super(id, name);
    }

    @Override
    public List<T> load(Pager pager) throws ManagerException {
        List<T> ret = null;

        beforeLoad();

        try {
            ret = doLoad(pager);
        } catch (Exception e) {
            throw new ManagerException(e);
            //Add return statement on 2014/12/18 by jgwang.
            //I think it should return if failed on doing update operation
        }

        try {
            afterLoad(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * This function is used to do some pre-handle you may need before executed really search operation, such as check
     * permission and so on
     *
     * @param
     * @return
     */
    public abstract void beforeLoad() throws ManagerException;

    /**
     * This function is used to execute really search operation by detail business rule
     *
     * @param
     * @return
     */
    public abstract List<T> doLoad(Pager pager) throws ManagerException;

    /**
     * This function is used to do some submit-handle you may need after executed really search operation such as
     * format data and so on
     *
     * @param list
     * @return
     */
    public abstract void afterLoad(List<T> list) throws ManagerException;

    /**
     * This function is used to do some pre-handle you may need before executed really get special record operation,
     * such as check permission and so on
     *
     * @param id
     * @return
     */
    @Override
    public T get(PK id) throws ManagerException {
        T ret = null;

        beforeGet(id);

        try {
            ret = doGet(id);
        } catch (Exception e) {
            throw new ManagerException(e);
            //Add return statement on 2014/12/18 by jgwang.
            //I think it should return if failed on doing update operation
        }

        try {
            afterGet(ret);
        } catch (Exception e) {
            e.printStackTrace();
            //todo: how to handle exception here
        }

        return ret;
    }

    /**
     * This function is used to execute really find operation by detail business rule
     *
     * @param id
     * @return
     */
    public abstract void beforeGet(PK id) throws ManagerException;
    /**
     * Get some special data record which record id is value of "id"
     *
     * @param id
     * @return
     */
    public abstract T doGet(PK id) throws ManagerException;

    /**
     * This function is used to do some submit-handle you may need after executed really get operation such as
     * format data and so on
     *
     * @param record
     * @return
     */
    public abstract void afterGet(T record) throws ManagerException;

    /**
     * Delete some data record from system
     *
     * @param id
     */
    @Override
    public void delete(PK id) throws ManagerException {
        beforeDelete(id);

        try {
            doDelete(id);
        } catch (Exception e) {
            throw new ManagerException(e);
            //Add return statement on 2014/12/18 by jgwang.
            //I think it should return if failed on doing update operation
        }

        try {
            afterDelete(id);
        } catch (Exception e) {
            e.printStackTrace();
            //todo : how to handle exception here
        }

    }

    /**
     * Batch delete data records from system
     *
     * @param ids
     */
    @Override
    public void delete(Collection<PK> ids) throws ManagerException {
        if(CommonUtils.isEmpty(ids)) {
            throw new ManagerException("No deleted record provided");
        }

        Iterator<PK> iter = ids.iterator();
        while (iter.hasNext()) {
            delete(iter.next());
        }
    }

    /**
     * This function is used to do some pre-handle you may need before executed really delete operation, such as check
     * permission and so on
     *
     * @param id
     * @return
     */
    public abstract void beforeDelete(PK id) throws ManagerException;

    /**
     * This function is used to do some submit-handle you may need after executed really delete operation
     *
     * @param id
     * @return
     */
    public abstract void afterDelete(PK id) throws ManagerException;

    /**
     * This function is used to execute really delete operation by detail business rule
     *
     * @param id
     * @return
     */
    public abstract void doDelete(PK id) throws ManagerException;

    /**
     * Post some special data record in system such as save or update
     *
     * @param record
     * @return
     */
    @Override
    public void post(T record, Object... addition) throws ManagerException {
        beforePost(record,addition);

        try {
            doPost(record,addition);
        } catch (Exception e) {
            throw new ManagerException(e);
            //Add return statement on 2014/12/18 by jgwang.
            //I think it should return if failed on doing update operation
        }

        try {
            afterPost(record,addition);
        } catch (Exception e) {
            e.printStackTrace();
            //todo : how to handle exception here
        }

    }

    /**
     * This function is used to do some pre-handle you may need before executed really submit operation, such as
     * record validation, check permission and so on
     *
     * @param record
     * @return
     */
    public abstract void beforePost(T record, Object... addition) throws ManagerException;

    /**
     * This function is used to do some submit-handle you may need after executed really submit operation, such as clean
     * operation and so on
     *
     * @param record
     * @return
     */
    public abstract void afterPost(T record, Object... addition) throws ManagerException;

    /**
     * This function is used to execute really update operation by detail business rule
     *
     * @param record
     * @return
     */
    public abstract void doPost(T record, Object... addition) throws ManagerException;

    /**
     * Create or add one new data record into system
     *
     * @param
     * @return T
     */
    @Override
    public T create(Object... args) throws ManagerException {
        T ret = null;
        beforeCreate(args);

        try {
            ret = doCreate(args);
        } catch (Exception e) {
            throw new ManagerException(e);
            //Add return statement on 2014/12/18 by jgwang.
            //I think it should return if failed on doing update operation
        }

        try {
            afterCreate(ret);
        } catch (Exception e) {
            e.printStackTrace();
            //to do: how to handle exception here
        }

        return ret;
    }

    /**
     * This function is used to do some pre-handle you may need before executed really create operation, such as
     * record information validation, check permission and so on
     *
     * @param
     * @return
     */
    public abstract void beforeCreate(Object... args) throws ManagerException;

    /**
     * This function is used to do some submit-handle you may need after executed really create operation
     *
     * @param
     * @return
     */
    public abstract void afterCreate(T record) throws ManagerException;

    /**
     * This function is used to execute really create operation by detail business rule
     *
     * @param
     * @return
     */
    public abstract T doCreate(Object... args) throws ManagerException;


    /**
     * This function is used to execute some special operation command aimed at special business module
     *
     * @param operation
     * @param args
     * @return
     */
    public Object execute(String operation, PK id, Map<String, Object> args) throws ManagerException {
        Object ret = null;
        beforeExecute(operation, id, args);

        try {
            ret = doExecute(operation, id, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ManagerException(e);
            //Add return statement on 2014/12/18 by jgwang.
            //I think it should return if failed on doing update operation
        }

        try {
            afterExecute(operation, id, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * This function is used to do some pre-handle you may need before executed really action operation, such as
     * check permission and so on
     *
     * @param operation
     * @param args
     * @return
     */
    public abstract void beforeExecute(String operation, PK id, Map<String, Object> args) throws ManagerException;

    /**
     * This function is used to execute really action operation by detail business rule
     *
     * @param operation
     * @param args
     * @return
     */
    public abstract Object doExecute(String operation, PK id, Map<String, Object> args) throws ManagerException;

    /**
     * This function is used to do some submit-handle you may need after executed really action operation
     *
     * @param operation
     * @param args
     * @return
     */
    public abstract void afterExecute(String operation, PK id, Map<String, Object> args) throws ManagerException;

    @Override
    public void update(T record, Object... addition) throws ManagerException {
        beforeUpdate(record, addition);

        try {
            doUpdate(record, addition);
        } catch (Exception e) {
            throw new ManagerException(e);
            //Add return statement on 2014/12/18 by jgwang.
            //I think it should return if failed on doing update operation
        }

        try {
            afterUpdate(record, addition);
        } catch (Exception e) {
            e.printStackTrace();
            //todo : how to handle exception here
        }

    }


    /**
     * This function is used to do some pre-handle you may need before executed really update operation, such as
     * record validation, check permission and so on
     *
     * @param record
     * @return
     */
    public abstract void beforeUpdate(T record, Object... addition) throws ManagerException;

    /**
     * This function is used to do some submit-handle you may need after executed really submit operation, such as clean
     * operation and so on
     *
     * @param record
     * @return
     */
    public abstract void afterUpdate(T record, Object... addition) throws ManagerException;

    /**
     * This function is used to execute really update operation by detail business rule
     *
     * @param record
     * @return
     */
    public abstract void doUpdate(T record, Object... addition) throws ManagerException;


    public void cacheSave(String key, Object value) {
        Map<String, Object> cache = localCache.get();
        cache.put(key,value);
    }

    public Object cacheDraw(String key) {
        Map<String, Object> cache = localCache.get();
        return cache.get(key);
    }

    protected void cacheDeleteRelatedData(Object value) {
        cacheSave(MGT_KEY_DELETED_DATA, value);
    }

    protected void cachePostRelatedData(Object value) {
        cacheSave(MGT_KEY_POSTED_DATA, value);
    }

    protected void cacheCreateRelatedData(Object value) {
        cacheSave(MGT_KEY_CREATED_DATA, value);
    }

    protected Object drawDeleteRelatedData() {
        return cacheDraw(MGT_KEY_DELETED_DATA);
    }

    protected Object drawPostRelatedData() {
        return cacheDraw(MGT_KEY_POSTED_DATA);
    }

    protected Object drawCreateRelatedData() {
        return cacheDraw(MGT_KEY_CREATED_DATA);
    }

}
