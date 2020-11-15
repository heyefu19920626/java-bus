package com.tang.config;

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
    @Bean(name = "configDb")
    @ConfigurationProperties(prefix = "spring.datasource.config-db")
    public DataSource buildUtilDataSource() {
        return DataSourceBuilder.create().build();
    }

    // @Bean(name = "commondb")
    // @ConfigurationProperties(prefix = "spring.datasource.commondb")
    // public DataSource buildCommon() {
    //     return DataSourceBuilder.create().build();
    // }

    @Bean(name = "busDb")
    @ConfigurationProperties(prefix = "spring.datasource.bus-db")
    public DataSource buildBusDataSource() {
        return DataSourceBuilder.create().build();
    }
}
