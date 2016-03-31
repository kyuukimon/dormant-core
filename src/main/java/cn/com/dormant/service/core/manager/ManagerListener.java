package cn.com.dormant.service.core.manager;

/**
 * <code>ManagerListener<code>
 *
 * @description: This class represent one manager listener for listening manager event such as create event, delete
 * event,post event or some special operation event and so on
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/03/04
 * @version: 1.0
 */
public interface ManagerListener {
    void onCreated(ManagerEvent event);

    void onDeleted(ManagerEvent event);

    void onUpdated(ManagerEvent event);

    void onPosted(ManagerEvent event);

    void onOperated(ManagerEvent event, String operation);
}
