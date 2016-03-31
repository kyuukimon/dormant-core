package cn.com.dormant.service.core.manager;

import java.io.Serializable;

/**
 * <code>DataRecord<code>
 *
 * @description: This class represent one base data record object
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/03/03
 * @version: 1.0
 */
public class DataRecord<PK> implements Serializable{
    private PK id   = null;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
}
