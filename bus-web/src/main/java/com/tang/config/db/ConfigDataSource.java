package com.tang.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * config.db的数据源配置类
 * <p>
 * 让我们的不同包名底下的mapper自动使用不同的数据源
 *
 * @author tang
 * @since 2020-07-18
 **/
@Configuration
@MapperScan(basePackages = {"com.tang.dao.config"}, sqlSessionFactoryRef = "sqlSessionConfig")
public class ConfigDataSource {
    @Resource
    @Qualifier("configDb")
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionConfig() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setVfs(SpringBootVFS.class);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateConfig() throws Exception {
        return new SqlSessionTemplate(sqlSessionConfig());
    }
}