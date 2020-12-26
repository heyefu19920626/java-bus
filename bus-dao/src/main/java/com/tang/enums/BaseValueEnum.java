package com.tang.enums;

/**
 * mybatis的获取值的接口
 *
 * @author tang
 * @since 2020-11.27-01:02
 */
public interface BaseValueEnum<T> {
    /**
     * 获取值
     *
     * @return 枚举值
     */
    int getValue();

    /**
     * 获取默认的枚举
     *
     * @return 默认的枚举
     */
    T getDefault();

    /**
     * 获取描述
     *
     * @return 描述
     */
    String getDesc();
}
