package cn.com.dormant.service.core.schedule;

import java.util.concurrent.TimeUnit;

/**
 * <code>${CLASS}<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/11/19
 * @version: 1.0
 */
public class ScheduleTest {
    public static void main(String[] args) {
        JobScheduler scheduler = new DefaultSystemJobScheduler();

        scheduler.schedule(new DefaultScheduleTask("TEST", 0, 10000, TimeUnit.MILLISECONDS) {
            @Override
            protected void execute() throws SysScheduleException{
                System.out.println("execute");
                throw new SysScheduleException("test exception");
            }
        });
    }
}
