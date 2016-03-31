/*****************************************************************************
 * HISTORY
 *
 * 2014/12/12   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.message.base;


import cn.com.dormant.service.core.message.MessageException;

import java.util.List;
import java.util.UUID;

/**
 * <code>BaseMsgTopic<code>
 *
 * @description: This class is one base implementation for MsgTopic interface
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/12/12
 * @version: 1.0
 */
public abstract class BaseMsgTopic implements MsgTopic{
    private String              id                      = null;

    private String              name                    = null;

    private boolean            enabled                = false;

    private MsgListenerManager listenerManager       = null;

    private MsgPublisher        publisher               = null;

    public BaseMsgTopic() {
        this.id = UUID.randomUUID().toString().replace("-","");
    }

    public BaseMsgTopic(String name) {
        this();
        this.name = name;
        this.listenerManager = new MsgListenerManager(this);
    }

    public BaseMsgTopic(String name, MsgPublisher publisher) {
        this();
        this.name = name;
        this.publisher = publisher;
        this.listenerManager = new MsgListenerManager(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void enable() {
        this.enabled = true;
    }

    @Override
    public void disable() {
        this.enabled = false;
    }

    @Override
    public boolean isValid() {
        return this.enabled;
    }

    public MsgListenerManager getListenerManager() {
        return listenerManager;
    }

    public void setListenerManager(MsgListenerManager listenerManager) {
        this.listenerManager = listenerManager;
    }

    public MsgPublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(MsgPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public MsgListener register(String name, MsgListener listener) {
        ((BaseMsgListener)listener).setTopic(this);
        try {
            return this.listenerManager.register(name, listener);
        }catch (MessageException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void unregister(String name) {
        this.listenerManager.unregister(name);
    }

    @Override
    public List<MsgListener> getListeners() {
        return this.getListenerManager().getListeners();
    }
}
