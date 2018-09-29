package com.datasource.multi.druid.mapper.ds3;

import com.datasource.multi.druid.model.ds3.Aa09ModelDS3;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //没有@Mapper注解，就需要在启动类上添加对应的自动扫描
@Repository//没有添加持久层注解@Repository ,虽然编译和运行都正常，但是在自动注入的的地方会显示红色下划线（很不爽）
public interface Aa09MapperDS3 {
    @Select("select * from aa09 where aaa100=#{aaa100}")
    @Results({
            @Result(column = "aaa100",property = "aaa100"),
            @Result(column = "aaa101",property = "aaa101"),
            @Result(column = "aaa104",property = "aaa104")
    })
    List<Aa09ModelDS3> getAa09ByAaa100(String aaa100);
}
