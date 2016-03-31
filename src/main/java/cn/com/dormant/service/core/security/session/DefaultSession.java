/*****************************************************************************
 * HISTORY
 *
 * 2014/09/19   Wang Jianguang --Create file and initial implementation
 *
 ****************************************************************************/
package cn.com.dormant.service.core.security.session;

import java.util.*;

/**
 * <code>LegacySession<code>
 *
 * @description: This class is used to maintain one session for each request
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/09/19
 * @version: 1.0
 */
public class DefaultSession implements Session {

    private String   id                       = null;

    private long    creationTime            = -1;

    private long    destroyTime             = -1;

    private long    lastAccessTime          = -1;

    private long    maxInactiveInterval    = -1;

    private boolean expired                 = false;

    private String   host                     = null;

    private Map<Object, Object> attributes   = null;

    private List<SessionListener> listeners  = null;

    public static final long DEFAULT_GLOBAL_SESSION_TIMEOUT = 10 * 60 * 1000;

    public DefaultSession() {
        this.id = UUID.randomUUID().toString().replace("-","");
        this.maxInactiveInterval = DEFAULT_GLOBAL_SESSION_TIMEOUT;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessTime = this.creationTime;

    }

    public DefaultSession(String host) {
        this();
        this.host = host;
    }

    @Override
    public void addListener(SessionListener listener) {
        if(listeners == null) {
            listeners = new ArrayList<SessionListener>();
        }
        listeners.add(listener);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public long getCreationTime() {
        return this.creationTime;
    }

    @Override
    public long getLastAccessTime() {
        return this.lastAccessTime;
    }

    @Override
    public long getMaxInactiveInterval() {
        return this.maxInactiveInterval;
    }

    @Override
    public void setMaxInactiveInterval(long maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public void touch() {
        this.lastAccessTime = System.currentTimeMillis();
    }

    @Override
    public void destroy() {
        if(destroyTime < 0) {
            this.destroyTime = System.currentTimeMillis();
        }

        this.attributes.clear();

        //todo: here need to enhance:
        // 1. Consider multi-threads and concurrent case
        // 2. May cause memory leak
        if(listeners != null) {
            for(SessionListener listener : listeners) {
                listener.sessionDestroyed(new SessionEvent(this));
            }
        }

        listeners.clear();
    }

    @Override
    public Collection<Object> getAttributeKeys() {
        if (attributes == null) {
            return Collections.emptySet();
        }
        return attributes.keySet();
    }

    @Override
    public Object getAttribute(Object key) {
        if(attributes == null) {
            return null;
        }
        return attributes.get(key);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        if(attributes == null) {
            attributes = new HashMap<Object,Object>();
        }

        attributes.put(key, value);
    }

    @Override
    public Object removeAttribute(Object key) {
        if(attributes == null) {
            return null;
        }
        return attributes.remove(key);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getDestroyTime() {
        return destroyTime;
    }

    public void setDestroyTime(long destroyTime) {
        this.destroyTime = destroyTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    @Override
    public boolean isExpired(){
        if(expired) {
            return true;
        }

        if(destroyTime > 0) {
            return true;
        }

        if (maxInactiveInterval > 0) {
            long expireTimeMillis = System.currentTimeMillis() - lastAccessTime;
            return (expireTimeMillis > maxInactiveInterval);
        }

        return false;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;

        if(expired) {
            if(listeners != null) {
                for(SessionListener listener : listeners) {
                    listener.sessionDestroyed(new SessionEvent(this));
                }
            }
        }
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean isValid() {
        if(destroyTime > 0) {
            return false;
        }

        if(isExpired()) {
            return false;
        }

        return true;
    }

}
