package com.datasource.multi.druid.config;

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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application-druid.properties")
@MapperScan(basePackages = "com.datasource.multi.druid.mapper.ds2",sqlSessionFactoryRef = "sqlSessionFactoryDS2")
public class DruidConfigurationDS2 {

    @Bean("dataSourceDS2")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")//application-druid.propeties 中的spring。datasource.ds1开头的所有的属性
    public DataSource dataSourceDS2(){
        return DruidDataSourceBuilder.create().build();//创建druid数据源
    }


    @Bean(name = "transactionManagerDS2")
    public DataSourceTransactionManager transactionManagerDS2() {
        return new DataSourceTransactionManager(dataSourceDS2());
    }

    @Bean(name = "sqlSessionFactoryDS2")
    public SqlSessionFactory sqlSessionFactoryDS2(@Qualifier("dataSourceDS2") DataSource  DataSourceDS2)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(DataSourceDS2);
/*        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));*/
        return sessionFactory.getObject();
    }
}
