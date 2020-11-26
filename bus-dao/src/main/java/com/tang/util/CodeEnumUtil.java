package com.tang.util;

import com.tang.enums.BaseValueEnum;

/**
 * 枚举转换工具类
 *
 * @author tang
 * @since 2020-11.27-01:03
 */
public class CodeEnumUtil {
    public static <E extends Enum<?> & BaseValueEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getValue() == code) {
                return e;
            }
        }
        return null;
    }
}
