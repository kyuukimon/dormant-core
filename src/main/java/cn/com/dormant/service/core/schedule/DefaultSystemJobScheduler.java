package cn.com.dormant.service.core.schedule;

import cn.com.dormant.service.core.misc.CommonUtils;

import java.util.*;
import java.util.concurrent.*;

/**
 * <code>DefaultSystemJobScheduler<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/11/19
 * @version: 1.0
 */
public class DefaultSystemJobScheduler implements JobScheduler {
    private final static int   SCHED_THREAD_POOL_SIZE = 2;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(SCHED_THREAD_POOL_SIZE);

    private ConcurrentMap<String, ScheduleTask> managedTasks = new ConcurrentHashMap<String, ScheduleTask>();


    @Override
    public void addTask(ScheduleTask task, boolean immediate) throws SysScheduleException {
        if(task == null) {
            throw new SysScheduleException("Task is null") ;
        }
        String taskId = task.getId();
        if(CommonUtils.isEmpty(taskId)) {
            throw new SysScheduleException("Task ID is null");
        }

        if(managedTasks.get(taskId) != null) {
            throw new SysScheduleException("The Task existed:" + taskId);
        }

        managedTasks.put(task.getId(), task);

        if(immediate) {
            schedule(task);
        }
    }

    @Override
    public ScheduleTask getTask(String taskId) throws SysScheduleException {
        return managedTasks.get(taskId);
    }

    @Override
    public void removeTask(ScheduleTask task) throws SysScheduleException {
        if(task == null) {
            throw new SysScheduleException("Task is null") ;
        }
        String taskId = task.getId();
        removeTask(taskId);
    }

    @Override
    public void removeTask(String taskId) throws SysScheduleException {
        if(CommonUtils.isEmpty(taskId)) {
            throw new SysScheduleException("Task ID is null");
        }

        ScheduleTask task = managedTasks.get(taskId);
        if(task == null) {
            throw new SysScheduleException("The Task is not existed:" + taskId);
        }

        managedTasks.remove(taskId);

        stop(task);

    }

    @Override
    public void removeAll() throws SysScheduleException {
        Set<String> keys = managedTasks.keySet();

        Iterator<String> iter = keys.iterator();
        while (iter.hasNext()) {
            String taskId = iter.next();
            removeTask(taskId);
        }
    }

    @Override
    public void schedule(ScheduleTask task) throws SysScheduleException {
        if(task == null) {
            throw new SysScheduleException("Task is null") ;
        }

        String taskId = task.getId();
        ScheduleTask managedTask = managedTasks.get(taskId);
        if(managedTask != null && managedTask.getState() == TaskState.RUNNING) {
            throw new SysScheduleException("The task has always scheduled and in running state:" + taskId);
        }

        Future future = null;
        long period = task.getPeriod();
        if(period > 0) {
            future = scheduler.scheduleAtFixedRate(task, task.getInitialDelay(), task.getPeriod(), task.getUnit());
        } else {
            future = scheduler.schedule(task,task.getInitialDelay(), task.getUnit());
        }

        synchronized (task) {
            task.setState(TaskState.RUNNING);
            task.setResult(future);
        }
    }

    @Override
    public void scheduleAll() throws SysScheduleException {
        Collection<ScheduleTask> values = managedTasks.values();

        Iterator<ScheduleTask> iter = values.iterator();
        while (iter.hasNext()) {
            ScheduleTask task = iter.next();
            schedule(task);
        }
    }

    @Override
    public void stop(ScheduleTask task) throws SysScheduleException {
        synchronized (task) {
            task.setState(TaskState.CANCELED);
            Future result = task.getResult();
            result.cancel(true);
        }

    }

    @Override
    public Set<String> getAllTaskIds() throws SysScheduleException {
        return this.managedTasks.keySet();
    }
}
