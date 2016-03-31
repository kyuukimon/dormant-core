package cn.com.dormant.service.core.schedule;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <code>ScheduleTask<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/11/19
 * @version: 1.0
 */
public interface ScheduleTask extends Runnable {
    String getId();

    long getInitialDelay();

    long getPeriod();

    TimeUnit getUnit();

    TaskState getState();

    void setState(TaskState state);

    Future getResult();

    void setResult(Future result);
}
