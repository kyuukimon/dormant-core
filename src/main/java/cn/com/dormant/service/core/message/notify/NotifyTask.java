package cn.com.dormant.service.core.message.notify;

/**
 * <code>NotifyTask<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/9/16
 * @version: 1.0
 */
public class NotifyTask<T> implements Runnable {
    private NotifyService<T> notifyService = null;

    private T message = null;

    private boolean isSuccess = false;

    private NotifyHandler<T> handler = null;

    public NotifyTask() {

    }

    public NotifyTask(NotifyService<T> notifyService) {
        this.notifyService = notifyService;
    }

    public NotifyTask(NotifyService<T> notifyService, T message) {
        this.notifyService = notifyService;
        this.message = message;
    }

    public NotifyTask(NotifyService<T> notifyService, T message, NotifyHandler<T> handler) {
        this.notifyService = notifyService;
        this.message = message;
        this.handler = handler;
    }

    public NotifyHandler<T> getHandler() {
        return handler;
    }

    public void setHandler(NotifyHandler<T> handler) {
        this.handler = handler;
    }

    @Override
    public void run() throws SysNotifyException{
        try {
            if(message != null) {
                isSuccess = notifyService.notify(message);
                handler.handle(this);
            }
        } catch (SysNotifyException e) {
            throw new SysNotifyException(e.getRootMsg());
        }
    }

    public NotifyService<T> getNotifyService() {
        return notifyService;
    }

    public void setNotifyService(NotifyService<T> notifyService) {
        this.notifyService = notifyService;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
