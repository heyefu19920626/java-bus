package com.heyefu.config.db;

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
@MapperScan(basePackages = {"com.heyefu.common"}, sqlSessionFactoryRef = "sqlSessionCommon")
public class CommonConfig {
    /**
     * 返回data1数据库的数据源
     *
     * @return
     */
    @Bean(name = "commondb")
    @Primary  //主数据源
    @ConfigurationProperties(prefix = "spring.datasource.commondb")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionCommon(@Qualifier("commondb") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db2/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplateCommon(@Qualifier("commondb") DataSource dataSource) throws Exception {
        return new SqlSessionTemplate(sqlSessionCommon(dataSource));
    }

    /**
     * 返回data1数据库的事务
     *
     * @param ds
     */
    @Bean(name = "commondbTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("commondb") DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}
