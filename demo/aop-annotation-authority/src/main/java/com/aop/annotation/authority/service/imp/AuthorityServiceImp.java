package com.aop.annotation.authority.service.imp;

import com.aop.annotation.authority.annotation.AuthorityAnnotation;
import com.aop.annotation.authority.service.AuthoriryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImp implements AuthoriryService {

    @Override
    @AuthorityAnnotation(authorityValue = "all")
    public boolean testAuthority() {

        return false;
    }

    @Override
    @AuthorityAnnotation(authorityValue = "update")
    public int update() {
        return 0;
    }

    @Override
    @AuthorityAnnotation(authorityValue = "delete")
    public int delete() {
        return 0;
    }

    @Override
    @AuthorityAnnotation(authorityValue = "add")
    public Integer add() {
        return 0;
    }

    @Override
    @AuthorityAnnotation(authorityValue = "find")
    public int find() {
        return 0;
    }

    @Override
    @AuthorityAnnotation(authorityValue = "build")
    public void build() {

    }

    @Override
    @AuthorityAnnotation(authorityValue = "getList")
    public List getList() {
        List list=new ArrayList<>();
        return list;
    }

    @Override
    @AuthorityAnnotation(authorityValue = "getNames")
    public String[] getNames() {
        return new String[0];
    }

    @Override
    @AuthorityAnnotation(authorityValue = "getNumber")
    public int[] getNumber() {
        return new int[0];
    }
}
