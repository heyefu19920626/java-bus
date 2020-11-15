package com.tang.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author tang
 * @since 2020-11.15-17:08
 */
@Configuration
@MapperScan(basePackages = {"com.tang.dao.user"}, sqlSessionFactoryRef = "sqlSessionBus")
public class BusDataSource {
    @Resource
    @Qualifier("busDb")
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionBus() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateBus() throws Exception {
        return new SqlSessionTemplate(sqlSessionBus());
    }
}