package com.datasource.multi.druid.mapper;

import com.datasource.multi.druid.mapper.ds1.MyMsgMapper;
import com.datasource.multi.druid.mapper.ds1.MyUsersMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
private static final Logger logger =Logger.getLogger(MapperTest.class);
    @Autowired
    private MyMsgMapper myMsgMapper;

    @Autowired
    private MyUsersMapper myUsersMapper;

    @Autowired
    private com.datasource.multi.druid.mapper.ds2.Aa09MapperDS2 aa09MapperDS2;

    @Autowired
    private com.datasource.multi.druid.mapper.ds3.Aa09MapperDS3 aa09MapperDS3;

    @Test
    public void getMsgById() {
        logger.info(">>>>>>>>>>>>myMsg>>>>>>>>>>>>>>开始");
        logger.info(">>>>>>>>>>>>>>>>>"+myMsgMapper.getMsgById(1008L));
        logger.info(">>>>>>>>>>>>>>myUsers>>>>>>>>>>>>开始");
        logger.info(">>>>>>>>>>>>>>>>>"+myUsersMapper.getUsersByName("wang"));
        logger.info(">>>>>>>>>>>>>>aa09MapperDS2.aa09>>>>>>>>>>>>开始");
        logger.info(">>>>>>>>>>>>>>>>>"+aa09MapperDS2.getAa09ByAaa100("EAZ216"));
        logger.info(">>>>>>>>>>>>>>aa09MapperDS3.aa09>>>>>>>>>>>>开始");
        logger.info(">>>>>>>>>>>>>>>>>"+aa09MapperDS2.getAa09ByAaa100("UPTYPE"));
    }
}