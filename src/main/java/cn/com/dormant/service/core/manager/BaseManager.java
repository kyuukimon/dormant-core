package cn.com.dormant.service.core.manager;

/**
 * <code>BaseManager<code>
 *
 * @description: This class is one base business manager
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2014/08/19
 * @version: 1.0
 */
public class BaseManager {
    /**
     * Identifier of manager
     */
    private String      id          = "0";

    /**
     * Name of manager
     */
    private String      name        = "Base Manager";

    /**
     * Description about manager such as its usage, functions provided and so on
     */
    private String      description = "This is base manager for managing business functions";

    public BaseManager() {

    }

    public BaseManager(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

