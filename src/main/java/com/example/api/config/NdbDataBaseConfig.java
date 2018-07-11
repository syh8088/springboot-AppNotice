package com.example.api.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * NDB 데이타베이스 환경설정
 */
@Configuration
public class NdbDataBaseConfig extends MyBatisConfig {

    /**
     * HikariConfig Bean
     *
     * @return HikariConfig
     */
    @Bean(name = "configNDB")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {

        return new HikariConfig();
    }

    /**
     * DataSource Bean
     *
     * @return dataSource
     */
    @Bean(name = "dsNDB")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(@Qualifier("configNDB") HikariConfig hikariConfig) {

        return new HikariDataSource(hikariConfig);
    }

    /**
     * SqlSessionFactory Bean
     *
     * @param dataSource
     * @param applicationContext
     * @return sqlSessionFactory
     * @throws Exception
     */
    @Primary
    @Bean(name = "sfNDB")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dsNDB") DataSource dataSource, ApplicationContext applicationContext) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sqlSessionFactoryBean, dataSource, applicationContext);
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * SqlSession Bean
     *
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "sessionNDB")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sfNDB") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
