package com.heyefu.config.db;

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
 * 让我们的不同包名底下的mapper自动使用不同的数据源
 *
 * @author tang
 * @since 2020-07-18
 **/
@Configuration
@MapperScan(basePackages = {"com.heyefu.dao.user"}, sqlSessionFactoryRef = "sqlSessionUser")
public class UtilsDataSource {
    @Resource
    @Qualifier("utildb")
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionUser() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateUser() throws Exception {
        return new SqlSessionTemplate(sqlSessionUser());
    }
}