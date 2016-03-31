/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message;

/**
 * <code>MsgConstants<code>
 *
 * @description: Message related constants
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class MsgConstants {
    public final static String TOPIC_PLATFORM_OPERATION            = "topic.platform.operation";


    public final static String MSG_TAG_PLATFORM_OPER_ADD          = "tag.platform.oper.create" ;

    public final static String MSG_TAG_PLATFORM_OPER_DEL          = "tag.platform.oper.delete" ;

    public final static String MSG_TAG_PLATFORM_OPER_UPDATE       = "tag.platform.oper.update" ;


    public final static String MSG_LISTENER_PLATFORM_OPER_ALL      = "listener.platform.oper.all";


    public final static String[] LISTENER_BINDING_KEY_PLATFORM_ALL    = {MSG_TAG_PLATFORM_OPER_ADD,
            MSG_TAG_PLATFORM_OPER_DEL,
            MSG_TAG_PLATFORM_OPER_UPDATE};

}
