package com.datasource.druid.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

/**
 *发现了Spring boot中有个注解@ConditionalOnProperty，这个注解能够控制某个configuration是否生效。
 * 具体操作是通过其两个属性name以及havingValue来实现的，
 * 其中name用来从application.properties中读取某个属性值，如果该值为空，则返回false;如果值不为空，
 * 则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。如果返回值为false，
 * 则该configuration不生效；为true则生效。
 * 当matchIfMissing = true时，即使没有该name属性也会加载正常
 * 当matchIfMissing = false时，必须要有对应的property(name,中的属性）
 *
 * @ConditionalOnProperty(name="spring.datasource.type",havingValue = "com.alibaba.druid.pool.DruidDataSource",matchIfMissing = true)
 * application.properties文件中配置了spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
 * matchIfMissing=true,那么，即使application.properties中没有spring.datasource.type这个属性，也会正常加载
 * 如果application.properties中有spring.datasource.type 这个属性，这个属性的值必须与 havingValue中的值一致才会加载
 */
@Configuration
@PropertySource("classpath:application-druid.properties")//一定要classpath:,不然有时会报找不到问题件application-druid.properties文件
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)//classpath 下有类的条件下，创建当前bean：  存在druidclass时，创建druid数据源实例
@ConditionalOnProperty(name="spring.datasource.type",havingValue = "com.alibaba.druid.pool.DruidDataSource",matchIfMissing = true)
public class LocalDruidDataSource {
    @Bean("dataSourceOne")
    @Primary////在同样的DataSource中，首先使用被标注的dataSourceOne
    @ConfigurationProperties("spring.datasource")  //获取配置信息里的属性，此处，获取application.properties文件中所有以spring.datasource开头的配置信息，用以初始化数据源
    public DataSource dataSourceOne(){// //解决 spring.datasource.filters=stat,wall,log4j 无法正常注册进去
        return DruidDataSourceBuilder.create().build();//构建druid 数据源
    }


    /**
     * 注册servlet
     */
    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean servletResgistrationBean =new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        //初始化参数
        //添加白名单
        servletResgistrationBean.addInitParameter("allow","127.0.0.1");
        //添加黑名单 //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletResgistrationBean.addInitParameter("","192.168.179.1");
        //设置druid登录的账号和密码
        servletResgistrationBean.addInitParameter("loginUsername","admin");
        servletResgistrationBean.addInitParameter("loginPassword","admin");
        //是否能够重置数据
        servletResgistrationBean.addInitParameter("resetEnable","false");
        return servletResgistrationBean;
    }

    /**
     * 注册filter bean
     */
    @Bean
    public FilterRegistrationBean druidFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new WebStatFilter());
        //添加过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //设置例外（即不拦截的路径）
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
