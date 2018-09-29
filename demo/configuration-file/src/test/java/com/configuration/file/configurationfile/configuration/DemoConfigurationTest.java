package com.configuration.file.configurationfile.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoConfigurationTest {

    @Autowired
    private DemoConfiguration demoConfiguration;
    @Test
    public void getMsg() {
      //  Assert.assertEquals("SpringBoot",demoConfiguration.getBookName());
       // Assert.assertEquals("wangsm",demoConfiguration.getStringValue());
        Assert.assertEquals("wangsm",demoConfiguration.getAuthor());
    }
}