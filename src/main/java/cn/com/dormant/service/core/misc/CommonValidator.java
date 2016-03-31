package cn.com.dormant.service.core.misc;

import java.util.Collection;
import java.util.Map;

/**
 * <code>CommonUtils<code>
 *
 * @description: This class includes some common util validation methods and maybe called by lots of place in system
 * @author: Wang Jianguang(aguang_1@126.com)
 * @creation: 2015/03/11
 * @version: 1.0
 */
public class CommonValidator {
    /**
     * 校验字符串是否为空
     *
     * @param s 需要校验的字符串，如：名称等等
     * @return true:为空
     * false：不为空
     */
    public static boolean isNull(String s) {
        if (null == s || s.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 检查Collection对象是否为空
     * 为空的可能包括集合为空，或者集合中没有对象
     *
     * @param c 需要检查的集合
     * @param
     * @return true表示为空，false表示不为空
     */
    public static boolean isNull(Collection c) {

        if (c == null || c.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 检查map是否为空
     *
     * @param map 需要检查的map
     * @return true表示为空，false表示不为空
     */
    public static boolean isNull(Map map) {

        if (map == null || map.isEmpty()) {
            return true;
        }
        return false;
    }

}
