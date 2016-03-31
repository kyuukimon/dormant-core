package cn.com.dormant.service.core.schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <code>DefaultScheduleTask<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/11/19
 * @version: 1.0
 */
public abstract class DefaultScheduleTask implements ScheduleTask {
    private String  id                = "";

    private long   initialDelay     = 0;

    private long    period           = 0;

    private TimeUnit unit             = TimeUnit.MILLISECONDS;

    private TaskState state           = TaskState.CREATED;

    private Future    result          = null;

    public DefaultScheduleTask(String id, long initialDelay, long period, TimeUnit unit) {
        this.id = id;
        this.initialDelay = initialDelay;
        this.period = period;
        this.unit = unit;
    }

    /**
     *
     * @param id
     * @param time "HH:MM:SS"
     */
    public DefaultScheduleTask(String id, String time) {
        this.id = id;
        this.period = 24 * 60 * 60 * 1000;
        this.initialDelay  = getTimeMillis(time) - System.currentTimeMillis();
        this.initialDelay = this.initialDelay > 0 ? this.initialDelay : this.period + this.initialDelay;
        this.unit = TimeUnit.MILLISECONDS;
    }

    private static long getTimeMillis(String time) {
        try {
            DateFormat dtFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dtFormat.parse(dFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public long getInitialDelay() {
        return this.initialDelay;
    }

    @Override
    public long getPeriod() {
        return this.period;
    }

    @Override
    public TimeUnit getUnit() {
        return this.unit;
    }

    @Override
    public TaskState getState() {
        return this.state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInitialDelay(long initialDelay) {
        this.initialDelay = initialDelay;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

    @Override
    public synchronized void setState(TaskState state) {
        this.state = state;
    }

    @Override
    public Future getResult() {
        return result;
    }

    @Override
    public void setResult(Future result) {
        this.result = result;
    }

    @Override
    public void run()  {
        synchronized (this) {
            if (state == TaskState.CANCELED) {
                return ;
            }
        }
        setState(TaskState.RUNNING);
        try {
            execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setState(TaskState.FINISHED);
    }


    protected abstract void execute() throws SysScheduleException;
}
