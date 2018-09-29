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
@MapperScan(basePackages = "com.datasource.multi.druid.mapper.ds3",sqlSessionFactoryRef = "sqlSessionFactoryDS3")
public class DruidConfigurationDS3 {

    @Bean("dataSourceDS3")
    @ConfigurationProperties(prefix = "spring.datasource.ds3")//application-druid.propeties 中的spring。datasource.ds1开头的所有的属性
    public DataSource dataSourceDS3(){
        return DruidDataSourceBuilder.create().build();//创建druid数据源
    }


    @Bean(name = "transactionManagerDS3")
    public DataSourceTransactionManager transactionManagerDS3() {
        return new DataSourceTransactionManager(dataSourceDS3());
    }

    @Bean(name = "sqlSessionFactoryDS3")
    public SqlSessionFactory sqlSessionFactoryDS3(@Qualifier("dataSourceDS3") DataSource  DataSourceDS3)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(DataSourceDS3);
/*        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));*/
        return sessionFactory.getObject();
    }
}
