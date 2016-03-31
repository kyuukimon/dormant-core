package cn.com.dormant.service.core.misc;

import cn.com.dormant.service.core.manager.DataRecord;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <code>CollectionAdjuster<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/6/4
 * @version: 1.0
 */
public class CollectionTidier<T extends DataRecord<PK>, PK> {
    public final static Integer TIDY_TYPE_DATA_SPLIT = 1;
    public final static Integer TIDY_TYPE_SPECIAL_HANDLE = 2;

    private Integer tidyType = TIDY_TYPE_DATA_SPLIT;

    private List<T> deletedDataSet = null;

    private List<T> updatedDataSet = null;

    private List<T> createdDataSet = null;

    public CollectionTidier(Integer tidyType) {
        this.tidyType = tidyType;
    }

    public void tidy(List<T> localDataSet, List<T> dataSet) {
        deletedDataSet = new ArrayList<>();
        updatedDataSet = new ArrayList<>();
        createdDataSet = new ArrayList<>();

        if (localDataSet == null) {
            localDataSet = new ArrayList<T>(0);
        }

        if (dataSet == null) {
            dataSet = new ArrayList<T>(0);
        }

        for (int i = localDataSet.size() - 1; i >= 0; i--) {
            T localRecord = localDataSet.get(i);

            boolean exist = false;
            for (int j = dataSet.size() - 1; j >= 0; j--) {
                T record = dataSet.get(j);
                if (compare(localRecord, record)) {
                    exist = true;
                    if (tidyType == TIDY_TYPE_DATA_SPLIT) {
                        this.updatedDataSet.add(localRecord);
                    } else {
                        handleUpdate(localRecord);
                    }

                    dataSet.remove(j);
                    localDataSet.remove(i);
                    break;
                }
            }
            if (!exist) {
                if (tidyType == TIDY_TYPE_DATA_SPLIT) {
                    this.createdDataSet.add(localRecord);
                } else if (tidyType == TIDY_TYPE_SPECIAL_HANDLE) {
                    handleCreate(localRecord);
                }
                localDataSet.remove(i);
            }
        }
        if (tidyType == TIDY_TYPE_DATA_SPLIT) {
            CollectionUtils.addAll(deletedDataSet, new Object[dataSet.size()]);
            Collections.copy(deletedDataSet, dataSet);
        } else if (tidyType == TIDY_TYPE_SPECIAL_HANDLE) {
            for (T t : dataSet) {
                handleDelete(t);
            }
        }

    }

    protected boolean compare(T a, T b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        PK aid = a.getId();
        PK bid = b.getId();

        if (aid.equals(bid)) {
            return true;
        }

        return false;
    }

    protected void handleUpdate(T obj) {
    }

    protected void handleDelete(T obj) {
    }

    protected void handleCreate(T obj) {
    }

    public List<T> getDeletedDataSet() {
        return deletedDataSet;
    }

    public List<T> getUpdatedDataSet() {
        return updatedDataSet;
    }

    public List<T> getCreatedDataSet() {
        return createdDataSet;
    }

    public static void main(String[] args) {
        CollectionTidier tidier = new CollectionTidier(CollectionTidier.TIDY_TYPE_DATA_SPLIT);
        List d = tidier.getDeletedDataSet();
    }
}
