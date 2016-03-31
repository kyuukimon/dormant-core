package cn.com.dormant.service.core.manager;

import java.io.Serializable;
import java.util.Map;

/**
 * <code>BillDataManager<code>
 *
 * @description: This class is one base special manager for bill feature related business
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/03/03
 * @version: 1.0
 */
public abstract class BillDataManager<M extends DataRecord<PK>,PK extends Serializable>
        extends DefaultDataManager<M,PK> implements DataSubmitter<M,PK> {
    private final static String MGT_KEY_BILL_CUR_MASTER = "CUR_MASTER";

    private final static String MGT_KEY_BILL_DETAILS = "DETAILS";

    private boolean placeInTransaction = false;

    private TransactionHelper transactionHelper;

//    @Autowired
//    private TransactionTemplate transactionTemplate;

    public BillDataManager() {
    }

    public BillDataManager(String id, String name) {
        super(id, name);
    }

    @Override
    public void doDelete(PK curMasterId) throws ManagerException {
        if(placeInTransaction) {
            if(transactionHelper != null) {
                transactionHelper.deleteInTransaction(this, curMasterId);
            } else {
                throw new ManagerException("Transaction object is null");
            }
        } else {
            submitDelete(curMasterId);
        }
    }

    @Override
    public void submitDelete(PK curMasterId) throws ManagerException {
        try{
            if(deleteMaster(curMasterId))
            {
                if(deleteDetail(curMasterId))
                {
                    return ;
                }
            }
        }
        catch(Exception e)
        {
            throw new ManagerException(e);
        }
    }

    protected abstract boolean deleteDetail(PK curMasterId) throws ManagerException;

    protected abstract boolean deleteMaster(PK curMasterId) throws ManagerException;

//    protected abstract Map getDetailInfo( M curMaster, Object... addition);

    @Override
    public void doPost(final M curMaster, final Object... addition) throws ManagerException {
        if(curMaster == null) {
            throw new ManagerException("The record is null,no update! ");
        }

        cacheSave(MGT_KEY_BILL_CUR_MASTER, curMaster);
        cacheSave(MGT_KEY_BILL_DETAILS, addition);

        if(placeInTransaction) {
            if(transactionHelper != null) {
                transactionHelper.saveInTransaction(this, curMaster, addition);
            } else {
                throw new ManagerException("Transaction object is null");
            }
        } else {
            submitSave(curMaster,addition);
        }

    }

    @Override
    public void doUpdate(M curMaster, Object... addition) throws ManagerException {
        if(curMaster == null) {
            throw new ManagerException("The record is null,no update! ");
        }

        cacheSave(MGT_KEY_BILL_CUR_MASTER, curMaster);
        cacheSave(MGT_KEY_BILL_DETAILS, addition);

        if(placeInTransaction) {
            if(transactionHelper != null) {
                transactionHelper.updateInTransaction(this, curMaster, addition);
            } else {
                throw new ManagerException("Transaction object is null");
            }
        } else {
            submitUpdate(curMaster,addition);
        }
    }

    @Override
    public boolean submitSave(M curMaster, Object... details) throws ManagerException{
        boolean bRet = false;
        boolean SH = true;

        if (submitDetail(details)) {
            if (submitMaster(curMaster)) {
                //If SH(Spring-Hibernate) architecture,it must be return true here
                //Because "submit" method is managed by "transactionInterceptor"
                //So,before finished execute "submit" method,dataset has not commited and you can not
                //to execute "afterPost" method
                if (SH) {
                    bRet = true;
                } else {
                    bRet = commitDataSet();
                    if (bRet) {
//                        bRet = afterPost(curMaster, details);
                    } else {
//                        this.setErrorMsg("Post dataset failure!");
                        bRet = false;
                    }
                }

            }
        }
        return bRet;
    }

    protected boolean commitDataSet() {
        //If not manage by tool as "Hibernate" or "Spring" and so on,
        //you must be manage transaction and submit dataset by yourself
        //to add code here to commit your dataset
        //Note: you need to add field "curMaster"  and field "details"
        //      in this class , as follow ,put "curMaster" parameter
        //		into "curMaster" field in "submitMaster" method and put
        //		"details" parameter into "details" field in "postDetail"
        //		method. At last ,commit "curMaster" and "details" in
        //		this method(commitDataSet).
        return true;
    }

    protected abstract boolean submitMaster(M curMaster) throws ManagerException;

    protected abstract boolean submitDetail(Object... details) throws ManagerException;

    @Override
    public boolean submitUpdate(M curMaster, Object... details) throws ManagerException{
        boolean bRet = false;
        boolean SH = true;
        if (submitDetailUpdate(details)) {
            if (submitMasterUpdate(curMaster)) {
                //If SH(Spring-Hibernate) architecture,it must be return true here
                //Because "submit" method is managed by "transactionInterceptor"
                //So,before finished execute "submit" method,dataset has not commited and you can not
                //to execute "afterPost" method
                if (SH) {
                    bRet = true;
                } else {
                    bRet = commitDataSet();
                    if (bRet) {
//                        bRet = afterPost(curMaster, details);
                    } else {
//                        this.setErrorMsg("Post dataset failure!");
                        bRet = false;
                    }
                }

            }
        }
        return bRet;
    }

    protected boolean submitMasterUpdate(M curMaster) throws ManagerException {
        return false;
    }

    protected boolean submitDetailUpdate(Object... details) throws ManagerException {
        return false;
    }

    @Override
    public Object submitExecute(String operation, PK id, Map<String, Object> args) throws ManagerException {
        return null;
    }

    protected M getCurMaster() {
        return (M)cacheDraw(MGT_KEY_BILL_CUR_MASTER);
    }

    protected Object[] getDetails() {
        return (Object[])cacheDraw(MGT_KEY_BILL_DETAILS);
    }

    public void setTransactionHelper(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public void setPlaceInTransaction(boolean placeInTransaction) {
        this.placeInTransaction = placeInTransaction;
    }
}
