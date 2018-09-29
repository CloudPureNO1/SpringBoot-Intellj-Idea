package com.indi.demo.db.config.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-druid.properties")
@MapperScan(basePackages = "com.indi.demo.db.mapper.ds1",sqlSessionFactoryRef = "sqlSessionFactoryDS1")//ds1数据源自动扫描的mapper路径，实现此路径下的mapper,自动使用这个数据源
public class DruidConfigurationDS1 {

    @Bean("dataSourceDS1")
    @ConfigurationProperties("spring.datasource.ds1")
    @Primary  //优先加载这个数据源，多个数据源，有且只有一个@primary 数据源
    public DataSource dataSourceDS1(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 设置数据源对应的SessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactoryDS1(@Qualifier("dataSourceDS1") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        //采用注解，就不用了
       // sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));//mybatis映射文件路径
        return sessionFactoryBean.getObject();
    }

    /**
     * 创建对应数据源的事物 Transaction
     * @return
     */
    @Bean(name="transactionManagerDS1")
    @Primary
    public DataSourceTransactionManager transactionManagerDS1(){
        return new DataSourceTransactionManager(this.dataSourceDS1());
    }
}
