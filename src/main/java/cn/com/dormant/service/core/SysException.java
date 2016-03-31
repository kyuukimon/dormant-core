package cn.com.dormant.service.core;

/**
 * <code>SysException<code>
 *
 * @description: Base unchecked runtime exception class
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/10/23
 * @version: 1.0
 */
public class SysException extends RuntimeException {
    public SysException(Throwable cause) {
        super(cause);
    }

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
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
