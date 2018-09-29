package com.indi.demo.db.mapper.ds2;

import com.indi.demo.db.model.ds2.NetAb01Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  //设置编译、运行时，扫描这个mapper,如果不添加这个注解，就需要在启动类上添加扫描mapper的路径
@Repository //持久层bean注解，一般在实现类上，不添加这个注解，编译和运行都正常，但是idea会在注入的地方显示红线，很不爽
public interface NetAb01Mapper {
    @Select("select * from net_ab01")
    @Results({
            @Result(id=true,column = "aab001",property = "aab001"),
            @Result(column = "aab004",property = "aab004")
    })
    List<NetAb01Model> getAll();
}
