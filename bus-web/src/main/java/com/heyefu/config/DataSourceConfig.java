package com.heyefu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author heyefu
 * Create in: 2020-07-17
 * Time: 下午11:56
 **/
@Configuration
public class DataSourceConfig {
    @Bean(name = "utildb")
    @ConfigurationProperties(prefix = "spring.datasource.utildb")
    public DataSource buildUtilDataSource() {
        return DataSourceBuilder.create().build();
    }

    // @Bean(name = "commondb")
    // @ConfigurationProperties(prefix = "spring.datasource.commondb")
    // public DataSource buildCommon() {
    //     return DataSourceBuilder.create().build();
    // }
}
