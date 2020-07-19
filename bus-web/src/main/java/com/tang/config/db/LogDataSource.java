package com.tang.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author tang
 * @since 2020-07-18
 **/
@Configuration
@MapperScan(basePackages = {"com.tang.dao.log"}, sqlSessionFactoryRef = "sqlSessionLog")
public class LogDataSource {
    /**
     * 返回data1数据库的数据源
     * 主数据源
     *
     * @return 数据源
     */
    @Bean(name = "logDb")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.log-db")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionLog(@Qualifier("logDb") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db2/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplateLog(@Qualifier("logDb") DataSource dataSource) throws Exception {
        return new SqlSessionTemplate(sqlSessionLog(dataSource));
    }

    /**
     * 返回data1数据库的事务
     *
     * @param ds 事物
     */
    @Bean(name = "logTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("logDb") DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}
