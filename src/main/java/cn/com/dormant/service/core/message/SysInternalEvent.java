/*****************************************************************************
 * HISTORY
 *
 * 2014/09/22   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message;

/**
 * <code>SysInternalEvent<code>
 *
 * @description: This class represent one event from system internal object
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/22
 * @version: 1.0
 */
public class SysInternalEvent<T> {//extends java.common.EventObject {


//    public SysInternalEvent(Object source) {
//        super(source);
//    }
//
//    @Override
//    public Object getSource() {
//        return super.getSource();
//    }

    /**
     * The object on which the Event initially occurred.
     */
    protected transient T  source;

    /**
     * Constructs a prototypical Event.
     *
     * @param    source    The object on which the Event initially occurred.
     * @exception  IllegalArgumentException  if source is null.
     */
    public SysInternalEvent(T source) {
        if (source == null)
            throw new IllegalArgumentException("null source");

        this.source = source;
    }

    /**
     * The object on which the Event initially occurred.
     *
     * @return   The object on which the Event initially occurred.
     */
    public T getSource() {
        return source;
    }

    /**
     * Returns a String representation of this EventObject.
     *
     * @return  A a String representation of this EventObject.
     */
    public String toString() {
        return getClass().getName() + "[source=" + source + "]";
    }
}
