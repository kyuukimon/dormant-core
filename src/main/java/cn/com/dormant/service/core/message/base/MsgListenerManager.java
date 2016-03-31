/*****************************************************************************
 * HISTORY
 *
 * 2014/12/17   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;

import cn.com.dormant.service.core.message.MessageException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <code>MsgListenerManager<code>
 *
 * @description: For each message topic, it may have lots of message listeners to subscribe and listen message
 * published by topic. So here need one manager to manage these listeners including register listener, unregister
 * listener,retrieving listeners and so on.
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public class MsgListenerManager {
    private Map<String, MsgListener> listeners = new HashMap<String, MsgListener>();

    private MsgTopic topic = null;

    public MsgListenerManager(MsgTopic topic) {
        this.topic = topic;
    }

    public MsgListener register(String name, MsgListener listener) throws MessageException {
        listeners.put(name, listener);

        return listener;
    }

    public void unregister(String name) {
        listeners.remove(name);
    }

    public MsgTopic getTopic() {
        return topic;
    }

    public List<MsgListener> getListeners() {
        if(this.listeners == null) {
            return null;
        }
        return Arrays.asList(this.listeners.values().toArray(new MsgListener[0]));
    }

}
