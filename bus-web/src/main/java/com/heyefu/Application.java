package com.heyefu;

import com.heyefu.config.DbInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午1:55
 **/
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class
})
public class Application {
    public static void main(String[] args) {
        init();
        SpringApplication.run(Application.class, args);
    }

    public static void init() {
        if (!new DbInit().init()) {
            System.out.println("init db error");
            System.exit(0);
        }
    }
}