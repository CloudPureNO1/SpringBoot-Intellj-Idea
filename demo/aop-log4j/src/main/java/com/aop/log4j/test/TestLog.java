package com.aop.log4j.test;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TestLog {
    private static final  Logger logger= Logger.getLogger(TestLog.class);

    public static void testLog(){
        logger.info(">>>>>>>>>>>>>>...test Console log》》》》》》》》》》》");
    }
}
