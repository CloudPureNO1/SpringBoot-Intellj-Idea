package com.aop.log4j;

import com.aop.log4j.test.TestLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopLog4jApplication {


    public static void main(String[] args) {
        SpringApplication.run(AopLog4jApplication.class, args);
       TestLog.testLog();

    }
}
