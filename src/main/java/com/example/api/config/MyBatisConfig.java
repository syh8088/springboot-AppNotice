package com.example.api.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.io.IOException;

public class MyBatisConfig {

    private static final String CONFIG_CLASSPATH = "classpath:mybatis/Configuration.xml";
    private static final String MAPPERS_CLASSPATH = "classpath:mybatis/mapper/*.xml";

    protected void configureSqlSessionFactory(SqlSessionFactoryBean sqlSessionFactoryBean, DataSource dataSource, ApplicationContext applicationContext) throws IOException {

        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(CONFIG_CLASSPATH));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(MAPPERS_CLASSPATH));
    }
}
