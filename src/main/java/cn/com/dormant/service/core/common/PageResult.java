package cn.com.dormant.service.core.common;

import java.util.List;

/**
 * <code>PageResult<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/5/18
 * @version: 1.0
 */
public class PageResult<T> {
    private Pager pager = null;

    private List<T> dataSet = null;

    public PageResult() {
    }

    public PageResult(Pager pager, List<T> dataSet) {
        this.pager = pager;
        this.dataSet = dataSet;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public List<T> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<T> dataSet) {
        this.dataSet = dataSet;
    }
}
