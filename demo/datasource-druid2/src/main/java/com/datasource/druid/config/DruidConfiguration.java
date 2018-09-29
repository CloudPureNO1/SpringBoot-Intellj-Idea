package com.datasource.druid.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-druid.properties")//一定要classpath:,不然有时会报找不到问题件application-druid.properties文件
public class DruidConfiguration {
    @Bean
    @Primary  //优先使用这个数据源
    @ConfigurationProperties("spring.datasource")//加载配置文件中，所有以spring.datasource开头的属性
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();//构建druid 数据源
    }
}
