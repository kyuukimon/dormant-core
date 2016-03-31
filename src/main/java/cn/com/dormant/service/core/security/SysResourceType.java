package cn.com.dormant.service.core.security;

/**
 * <code>SysResourceType<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/5/18
 * @version: 1.0
 */
public enum SysResourceType {
    SUB_SYSTEM("1", "System"),
    MODULE("2", "Module"),
    ORG("3", "Organization");

    private String id = null;

    private String name = null;

    private SysResourceType(String id, String name) {
        this.id = id;
        this.name = name ;
    }


    @Override
    public String toString() {
        return id;
    }

    public static void main(String[] args) {
        System.out.println(SysResourceType.MODULE);
    }
}
