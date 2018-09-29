package com.aop.annotation.authority.service;

import java.util.List;

public interface AuthoriryService {
     boolean testAuthority();
     int update();
     int delete();
     Integer  add();
     int find();
     void build();
     List getList();
     String [] getNames();
     int [] getNumber();


}
