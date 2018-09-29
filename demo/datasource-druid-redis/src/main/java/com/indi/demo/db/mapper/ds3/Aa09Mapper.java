package com.indi.demo.db.mapper.ds3;

import com.indi.demo.db.model.ds3.Aa09Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Aa09Mapper {
    @Select("select * from aa09 where aaa100=#{aaa100}")
    @Results({
            @Result(column = "aaa100",property = "aaa100"),
            @Result(column = "aaa101",property = "aaa101")
    })
    List<Aa09Model> getAa09ByAaa100(String aaa100);
}
