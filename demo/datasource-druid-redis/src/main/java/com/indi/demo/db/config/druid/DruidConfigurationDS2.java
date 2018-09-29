package com.indi.demo.db.config.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.ResourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-druid.properties")
@MapperScan(basePackages = "com.indi.demo.db.mapper.ds2",sqlSessionFactoryRef = "sqlSessionFactoryDS2")
public class DruidConfigurationDS2 {
    /**
     * 创建DataSource 实例
     */
    @Bean("dataSourceDS2")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 创建数据源的工厂实例
     */
    @Bean(name="sqlSessionFactoryDS2")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSourceDS2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        //此处采用注解，就不用设置实体映射文件了，
        //sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sessionFactoryBean.getObject();
    }

    /**
     * 创建数据源的事物
     */
    @Bean("transactionDS2")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(this.dataSource());
    }

}
