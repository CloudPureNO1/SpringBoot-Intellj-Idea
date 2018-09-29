package com.aop.annotation.authority;

import com.aop.annotation.authority.bean.AuthorityBean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthorityUtil {
    public List<AuthorityBean>  getAuthorityList(){
        List<AuthorityBean>authorityList=new ArrayList<AuthorityBean>();
        AuthorityBean bean= new AuthorityBean();
        bean.setValue("update");
        authorityList.add(bean);

        bean=new AuthorityBean("insert");
        authorityList.add(bean);

        bean=new AuthorityBean("delete");
        authorityList.add(bean);

        bean=new AuthorityBean("find");
        authorityList.add(bean);


        return authorityList;
    }
}
