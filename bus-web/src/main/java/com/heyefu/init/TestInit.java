/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.heyefu.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author h30002440
 * @version [eService  3.0.10, 2020/7/18]
 * @since 2020/7/18
 */
@Slf4j
@Component
@Order(2)
public class TestInit implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String sql = "";
        // jar包中不能使用文件方式读取，只能用流式读取
        // String sql = FileUtils
        //     .readFileToString(ResourceUtils.getFile("classpath:sql/user.sql"), StandardCharsets.UTF_8);
        log.info("read sql {}", sql);
    }
}
