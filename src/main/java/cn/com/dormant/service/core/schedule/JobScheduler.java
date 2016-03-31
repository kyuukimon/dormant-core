package cn.com.dormant.service.core.schedule;

import java.util.Set;

/**
 * <code>JobScheduler<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/11/19
 * @version: 1.0
 */
public interface JobScheduler {
    void addTask(ScheduleTask task, boolean immediate) throws SysScheduleException;

    void removeTask(ScheduleTask task) throws SysScheduleException;

    void removeTask(String taskId) throws SysScheduleException;

    void removeAll() throws SysScheduleException;

    void schedule(ScheduleTask task) throws SysScheduleException;

    void scheduleAll() throws SysScheduleException;

    void stop(ScheduleTask task) throws SysScheduleException;

    ScheduleTask getTask(String taskId) throws SysScheduleException;

    Set<String> getAllTaskIds() throws SysScheduleException;

}
