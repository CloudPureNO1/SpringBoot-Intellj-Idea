package com.log.log4j.multienviroments.log4.jmultienviroments;

import com.log.log4j.multienviroments.log4.jmultienviroments.goods.Goods;
import com.log.log4j.multienviroments.log4.jmultienviroments.user.User;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Log4jMultienviromentsApplicationTests {
private Logger logger=Logger.getLogger(Log4jMultienviromentsApplicationTests.class);
    @Test
    public void contextLoads() {

        logger.info("================starting test=========================");
        User user=new User();
        user.userMath();
        logger.debug("*******************User test complite***************");
        Goods goods=new Goods();
        goods.goodsMath();
        logger.debug("*******************Goods test complite***************");

        logger.error("++++++++++++++++测试发生异常+++++++++++++++++++++++++++++++");
        logger.info("================testing end=========================");
    }

}
