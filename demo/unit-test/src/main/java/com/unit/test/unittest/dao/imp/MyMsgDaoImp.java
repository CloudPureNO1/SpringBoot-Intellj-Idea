package com.unit.test.unittest.dao.imp;

import com.unit.test.unittest.bean.MyMsgBean;
import com.unit.test.unittest.dao.IMyMsgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //用于持久层
public class MyMsgDaoImp implements IMyMsgDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(MyMsgBean bean) {
        return jdbcTemplate.update("insert into my_msg values(?,?,?)",bean.getMsgId(),bean.getMsgName(),bean.getMsgDetail());
    }

    @Override
    public int del(Long id) {
        return jdbcTemplate.update("delete from my_msg where msg_id=?",id);
    }

    @Override
    public int chg(MyMsgBean bean) {
        return jdbcTemplate.update("update my_msg set msg_name=? ,msg_detail=? where msg_id=?",bean.getMsgName(),bean.getMsgDetail(),bean.getMsgId());
    }

    @Override
    public MyMsgBean findBeanById(Long id) {
        List<MyMsgBean> list= jdbcTemplate.query("select * from my_msg where msg_id=?",new Object[]{id},new BeanPropertyRowMapper(MyMsgBean.class));
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<MyMsgBean> findAll() {
        return  jdbcTemplate.query("select * from my_msg",new Object[]{},new BeanPropertyRowMapper(MyMsgBean.class));
    }
}
