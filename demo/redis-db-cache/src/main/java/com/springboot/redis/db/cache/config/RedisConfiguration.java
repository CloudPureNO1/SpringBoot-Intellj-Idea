package com.springboot.redis.db.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {



    @Bean
    public RedisTemplate<Object, Object> myRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();

        return template;
    }



 


    /**
     * 查看KeyGenerator.java
     * Generate a key for the given method and its parameters.
     * param ：target the target instance
     * param ：method the method being called
     * param ：params the method parameters (with any var-args expanded)
     * return ：a generated key
     *
     * 	Object generate(Object target, Method method, Object... params);
     */
    /**
     * Redis缓存key 的生成方法
     * 采用：包名.类名.方法还回类型.方法名.方法参数类型（防止其他应用等与之相同）
     * 包名中又包含了 项目名 公司简称等信息
     * @return
     */
    @Bean
    public KeyGenerator getRedisKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... objects) {
                StringBuffer buffer=new StringBuffer();
                //为了防止分布式或其他系统，出现相同的key，这里采用自己生产与项目相关的key
                //包名.类名.方法还回类型.方法名.方法参数类型
                //buffer.append(target.getClass().getPackage().getName());//路径名
                buffer.append(target.getClass().getName());//类名(此处取到的是类的全名，即包含包的比如：demo.Test)
                buffer.append(".");//分割符（采用包.类）
                /**
                 * 方法的返回类型，如果是一个对象，会包含对应的包（把包去掉）
                 */
                String returnType= method.getReturnType().getName();
                buffer.append(returnType.substring(returnType.lastIndexOf(".")+1));//方法的还回类型
                buffer.append(".");//分割符（采用包.类）
                buffer.append(method.getName());//方法名
                if(objects!=null&&objects.length>0){
                    for(Object obj:objects){
                        String temp=obj.getClass().getName();
                        buffer.append(temp.substring(temp.lastIndexOf(".")));//参数类型（包含前面的点号）
                    }
                }
                return buffer.toString();
            }
        };




    }









}
