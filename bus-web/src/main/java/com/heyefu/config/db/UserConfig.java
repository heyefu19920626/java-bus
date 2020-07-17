package com.heyefu.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author tang
 * @since 2020-07-18
 **/
@Configuration
// 让我们的不同包名底下的mapper自动使用不同的数据源
@MapperScan(basePackages = {"com.heyefu.user"}, sqlSessionFactoryRef = "sqlSessionUser")
public class UserConfig {
    @Autowired
    @Qualifier("utildb")
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionUser() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db1/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateUser() throws Exception {
        return new SqlSessionTemplate(sqlSessionUser());
    }
}
