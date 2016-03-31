package cn.com.dormant.service.core.manager;

/**
 * <code>DataOperation<code>
 *
 * @description:
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/7/10
 * @version: 1.0
 */
public enum DataOperation {
    OPER_LOAD("load"),
    OPER_DETAIL("detail"),
    OPER_DELETE("delete"),
    OPER_POST("post"),
    OPER_UPDATE("update"),
    OPER_CREATE("create"),
    OPER_EXECUTE("execute");

    private String code;

    private DataOperation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static DataOperation get(String code) {
        DataOperation ret = null;
        DataOperation[] values = DataOperation.values();
        for (DataOperation val : values) {
            if (val.getCode().equals(code)) {
                ret = val;
                break;
            }
        }
        return ret;
    }
}
