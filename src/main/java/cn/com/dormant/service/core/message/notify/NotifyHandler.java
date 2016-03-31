package cn.com.dormant.service.core.message.notify;

/**
 * <code>${CLASS}<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/11/19
 * @version: 1.0
 */
public interface NotifyHandler<T> {

    void handle(NotifyTask<T> task) throws SysNotifyException;
}
