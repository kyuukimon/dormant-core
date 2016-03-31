/*****************************************************************************
 * HISTORY
 *
 * 2014/12/12   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;


import cn.com.dormant.service.core.message.MessageConfig;
import cn.com.dormant.service.core.message.MessageException;

import java.util.List;
import java.util.Set;

/**
 * <code>MessageCenter<code>
 *
 * @description: In our system, we want to provide one loose coupling solution based message communication mechanism
 * between components, and cross platform as better. In addition, we adopt Topic mode to manage message related
 * operations. In this mode, each business component may apply one message topic service from one message center like
 * organization.
 *   So, here we define one message center which service for topic owner or applicant. Only registered topic can provide
 * business message service for outside users such as publish business messages and so on.
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public interface MessageCenter {
    MsgTopic register(String topicName, MsgTopic topic) throws MessageException;

    MsgTopic register(String topicName) throws MessageException;

    void unregister(String topicName);

    void enable(MsgTopic topic);

    void disable(MsgTopic topic);

    MsgTopic getTopic(String name);

    boolean isValidTopic(String name);

    MessageConfig getConfig();

    Set<String> getAllTopicNames();

}
