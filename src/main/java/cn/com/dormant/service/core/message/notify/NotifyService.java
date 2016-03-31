package cn.com.dormant.service.core.message.notify;

/**
 * <code>NotifyService<code>
 *
 * @description: Notify service such as email notification, mobile notification and so on
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/01/23
 * @version: 1.0
 */
public interface NotifyService<T> {
    /**
     * Execute notification service
     * @param msg Message including message header, message body and so on.
     */
    boolean notify(T msg) throws SysNotifyException;
}
