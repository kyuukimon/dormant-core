package cn.com.dormant.service.core.manager;

import cn.com.dormant.service.core.message.SysInternalEvent;

/**
 * <code>ManagerEvent<code>
 *
 * @description: This class represent one manager event which caused by some manager operation or actions
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/03/04
 * @version: 1.0
 */
public class ManagerEvent<T> extends SysInternalEvent<T> {
    private String operation = null;

    public ManagerEvent(T source) {
        super(source);
    }

    public ManagerEvent(T source, String operation) {
        super(source);
        this.operation = operation;
    }

    @Override
    public T getSource() {
        return super.getSource();
    }
}
