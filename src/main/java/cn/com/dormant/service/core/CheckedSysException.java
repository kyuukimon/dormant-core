package cn.com.dormant.service.core;


/**
 * <code>CheckedSysException<code>
 *
 * @description: Base checked exception class
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/10/23
 * @version: 1.0
 */
public class CheckedSysException extends Exception {
    public CheckedSysException(Throwable cause) {
        super(cause);
    }

    public CheckedSysException(String message) {
        super(message);
    }

    public CheckedSysException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getRootMsg(){
        Throwable e = this;
        while (e.getCause() != null){
            e = e.getCause();
        }
        return e.getMessage();
    }
}
