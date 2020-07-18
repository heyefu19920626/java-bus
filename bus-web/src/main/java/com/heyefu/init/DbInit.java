/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.heyefu.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author h30002440
 * @version [eService  3.0.10, 2020/7/18]
 * @since 2020/7/18
 */
@Order(1)
@Component
public class DbInit implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        if (!new com.heyefu.config.DbInit().init()) {
            System.out.println("init db error");
            System.exit(0);
        }
    }
}
