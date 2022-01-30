package com.tang.spi;

/**
 * spi(Service Provider Interface) 接口
 *
 * @author heyefu
 * @version spi
 * @since 2022/1/30
 */
public interface IService {
    /**
     * 打印
     *
     * @param s 字符串
     */
    void print(String s);
}