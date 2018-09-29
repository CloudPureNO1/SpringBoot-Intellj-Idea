package com.log.log4j.log.log4j.user;

import org.apache.log4j.spi.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import org.apache.log4j.Logger;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestTest {
    private static final Logger logger=Logger.getLogger(UserTest.class);
    @Test
    public void setValue() {
        logger.info("输出info");
        logger.debug("输出debug");
        logger.error("输出error");
    }
}