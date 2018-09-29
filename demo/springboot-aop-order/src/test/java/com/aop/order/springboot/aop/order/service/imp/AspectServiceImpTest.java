package com.aop.order.springboot.aop.order.service.imp;

import com.aop.order.springboot.aop.order.service.AspectService;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectServiceImpTest {

    @Autowired
    private AspectService aspectService;


    @Test
    public void setMsg(){
        aspectService.setMsg("冯.布劳恩","V2");
        throw new NullPointerException("aop 测试后置抛出异常通知");
    }
}