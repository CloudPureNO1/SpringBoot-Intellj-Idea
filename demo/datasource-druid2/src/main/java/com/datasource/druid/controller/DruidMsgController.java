package com.datasource.druid.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 如何获取 Druid 的监控数据
 * Druid 的监控数据可以通过 DruidStatManagerFacade 进行获取，获取到监控数据之后你可以将其暴露给你的监控系统进行使用。
 * Druid 默认的监控系统数据也来源于此。下面给做一个简单的演示，在 Spring Boot 中如何通过 HTTP 接口将 Druid 监控数据以 JSON 的形式暴露出去，
 * 实际使用中你可以根据你的需要自由地对监控数据、暴露方式进行扩展。
 */
@Controller
@RequestMapping(value="/druidSystem")
public class DruidMsgController {
    private static final  Logger logger=Logger.getLogger(DruidMsgController.class);


    @GetMapping(value="/druidStat")
    @ResponseBody
    public Object druidStat(){
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        logger.info(">>>>>>>>>>>.."+DruidStatManagerFacade.getInstance().getDataSourceStatDataList().toString());
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
