package com.aop.annotation.authority.service.imp;

import com.aop.annotation.authority.service.AuthoriryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityServiceImpTest {

    @Autowired
    private AuthoriryService authorityService;

    @Test
    public void testAuthority() {
        System.out.println(">>>>>>>>>>>>测试all>>>>>>>>>>>>>>>>>");
        authorityService.testAuthority();
    }

    @Test
    public void update() {
        System.out.println(">>>>>>>>>>>>测试  update>>>>>>>>>>>>>>>>>");
        authorityService.update();
    }

    @Test
    public void delete() {
        System.out.println(">>>>>>>>>>>>测试  delete>>>>>>>>>>>>>>>>>");
        authorityService.delete();
    }

    @Test
    public void add() {
        System.out.println(">>>>>>>>>>>>测试  add>>>>>>>>>>>>>>>>>");
        authorityService.add();
    }

    @Test
    public void find() {
        System.out.println(">>>>>>>>>>>>测试  find>>>>>>>>>>>>>>>>>");
        authorityService.find();
    }

    @Test
    public void build() {
        System.out.println(">>>>>>>>>>>>测试  build>>>>>>>>>>>>>>>>>");
        authorityService.build();;
    }

    @Test
    public void getList() {
        System.out.println(">>>>>>>>>>>>测试  List>>>>>>>>>>>>>>>>>");
         authorityService.getList();
    }

    @Test
    public void  getNames() {
        System.out.println(">>>>>>>>>>>>测试  String[]>>>>>>>>>>>>>>>>>");
         authorityService.getNames();
    }

    @Test
    public void getNumber() {
        System.out.println(">>>>>>>>>>>>测试  int[]>>>>>>>>>>>>>>>>>");
         authorityService.getNumber();
    }
}