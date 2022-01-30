package com.tang.spi;

/**
 * 打印实现
 *
 * @author heyefu
 * @version spi
 * @since 2022/1/30
 */
public class IServiceImpl implements IService {

    @Override
    public void print(String s) {
        System.out.printf("%s from IServiceImpl\n", s);
    }
}