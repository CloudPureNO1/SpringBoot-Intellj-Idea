package com.indi.demo.db.config.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-druid.properties")
@MapperScan(basePackages = "com.indi.demo.db.mapper.ds3",sqlSessionFactoryRef = "sqlSessionFactoryDS3")
public class DruidConfigurationDS3 {

    @Bean("dataSourceDS3")
    @ConfigurationProperties(prefix = "spring.datasource.ds3")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    //用数据源ds3创建的数据源DataSouce 创建SqlSessionFactory实例
    @Bean("sqlSessionFactoryDS3")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(this.dataSource());
        return sessionFactoryBean.getObject();
    }

    //创建数据源ds3的事物
    @Bean("transactionDS3")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(this.dataSource());
    }
}
