package com.springboot.redis;

import com.springboot.redis.service.UserBean;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;



    @Autowired
    private UserBean userBean;

    /**
    *解决spring boot redis序列化key-value乱码\xac\xed\x00\x05t\x00
     * 如果加上这个，会导致缓存实体bean发生 反序列化异常
     * 比如：
     * java.lang.ClassCastException: com.springboot.redis.service.UserBean cannot be cast to java.lang.String
     *
     */

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        /**
         * 设置key的序列化为String，保障key值是String类型的
         */
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        //设置为String类型的反序列化，其他类型的就不能缓存
       // redisTemplate.setValueSerializer(stringSerializer);
        //redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }








    @Test
    public void contextLoads() {
        //设置Redis string-value 值
        ValueOperations<String, Object>valueOperations= redisTemplate.opsForValue();
        valueOperations.set("test","测试");
        valueOperations.set("msg","This is a test!");
       // Assert.assertEquals("Pluto1",redisTemplate.opsForValue().get("plant"));

    }

    @Test
    public void setCacheTime(){
        //设置缓存时间
        redisTemplate.expire("msg",10,TimeUnit.SECONDS);//设置furit的缓存时间为10秒
        System.out.println(">>>>>>>>>>>>>>>>before>>>>>>>>"+redisTemplate.opsForValue().get("msg"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>>>>>>>>>>>>>after>>>>>>>"+redisTemplate.opsForValue().get("msg"));
    }
    @Test
    public void delete(){
        //删除缓存的key
        redisTemplate.opsForValue().set("city","HangZhou");
        redisTemplate.opsForValue().set("lake","XiHu");
        Assert.assertEquals("HangZhou",redisTemplate.opsForValue().get("city"));
        if(redisTemplate.hasKey("city")){
            redisTemplate.delete("city");
        }
        Assert.assertEquals("HangZhou",redisTemplate.opsForValue().get("city"));
    }


    @Test
    public void list(){
        redisTemplate.opsForList().rightPush("userName","wangsm");
        redisTemplate.opsForList().rightPush("userName","wangxy");
        redisTemplate.opsForList().rightPush("userName","wangyz");
        redisTemplate.opsForList().rightPush("userName","wangsenming");

        List<String> list=redisTemplate.opsForList().range("userName",1,2);
        if(!StringUtils.isEmpty(list)){
            for(String str:list){
                System.out.println(">>>>>>>>>>>>>>>>"+str);
            }
        }
    }

    @Test
    public void set(){
        redisTemplate.opsForSet().add("book",new String[]{"Spring","Spring Boot","SpringMVC","Redis"});
        Set<String> set=redisTemplate.opsForSet().members("book");
        if(!set.isEmpty()){
            Iterator<String>it=set.iterator();
            while(it.hasNext()){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+it.next());
            }
        }
    }

    @Test
    public void hash(){
        redisTemplate.opsForHash().put("plant","水星".hashCode()+"","水星");
        redisTemplate.opsForHash().put("plant","金星".hashCode()+"","金星");
        redisTemplate.opsForHash().put("plant","地球".hashCode()+"","地球");
        redisTemplate.opsForHash().put("plant","火星".hashCode()+"","火星");
        redisTemplate.opsForHash().put("plant","木星".hashCode()+"","木星");
        redisTemplate.opsForHash().put("plant","土星".hashCode()+"","土星");
        redisTemplate.opsForHash().put("plant","天王星".hashCode()+"","天王星");
        redisTemplate.opsForHash().put("plant","海王星".hashCode()+"","海王星");
        redisTemplate.opsForHash().put("plant","冥王星".hashCode()+"","冥王星");

        List<String>list=redisTemplate.opsForHash().values("plant");
        if(list!=null&&list.size()>0){
            for(String str:list){
                System.out.println(">>>>>>>>>>>>>>>>"+str);
            }
        }


       Assert.assertEquals("火星", redisTemplate.opsForHash().get("plant","水星".hashCode()+""));
    }

    //有序集合
    @Test
    public void zSet(){
        redisTemplate.opsForZSet().add("fruit","苹果",1d);
        redisTemplate.opsForZSet().add("fruit","香蕉",2d);
        redisTemplate.opsForZSet().add("fruit","梨子",3d);
        redisTemplate.opsForZSet().add("fruit","桃子",4d);
        redisTemplate.opsForZSet().add("fruit","樱桃",5d);
        redisTemplate.opsForZSet().add("fruit","李子",6d);


       Set<String>set= redisTemplate.opsForZSet().range("fruit",2,5);
       if(!set.isEmpty()){
           Iterator<String>it=set.iterator();
           while(it.hasNext()){
               System.out.println(">>>>>>>>>>>>>>>>>>"+it.next());
           }
       }
    }



    @Test
    public void bean(){

        userBean.setAge(12);
        userBean.setId(1001L);
        userBean.setUserName("wangsm");
        userBean.setPasswd("123456");
        redisTemplate.opsForValue().set("user"+userBean.getId(),userBean);
        userBean.setAge(12);
        userBean.setId(1002L);
        userBean.setUserName("wxy");
        userBean.setPasswd("123456");
        redisTemplate.opsForValue().set("user"+userBean.getId(),userBean);

        UserBean user= (UserBean) redisTemplate.opsForValue().get("user1002");
        System.out.println(">>>>>>>>>>>>>>>>>>"+user.getUserName());
    }



    @Test
    public void tsetString(){
        userBean.setAge(12);
        userBean.setId(1002L);
        userBean.setUserName("wxy");
        userBean.setPasswd("123456");
        stringRedisTemplate.opsForValue().set("testName",JSONObject.fromObject(userBean).toString());


        String userString= stringRedisTemplate.opsForValue().get("testName");
    }



}
