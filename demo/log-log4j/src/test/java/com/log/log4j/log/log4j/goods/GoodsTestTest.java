package com.log.log4j.log.log4j.goods;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsTestTest {
    private static final  Logger logger= Logger.getLogger(GoodsTestTest.class);

    @Test
    public void setValue() {
        logger.info(">>>>>>>>>>>>>Goods INFO 级别日志");
        logger.debug(">>>>>>>>>>>>Goods Debug 级别日志");
        logger.error(">>>>>>>>>>>>>>>>Goods Error 级别日志");
    }
}