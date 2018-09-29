package com.unit.test.unittest.service.imp;

import com.unit.test.unittest.bean.MyMsgBean;
import com.unit.test.unittest.dao.IMyMsgDao;
import com.unit.test.unittest.dao.imp.MyMsgDaoImp;
import com.unit.test.unittest.service.MyMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyMsgServiceImp implements MyMsgService {

    @Autowired
    IMyMsgDao myMsgDao;

    @Override
    public boolean dmlMyMsg(MyMsgBean bean,String type) {
        if("update".equals(type.toLowerCase())){
           return myMsgDao.chg(bean)>0?true:false;
        }else if("delete".equals(type.toLowerCase())){
            return myMsgDao.del(bean.getMsgId())>0?true:false;
        }else if("insert".equals(type.toLowerCase())){
            return myMsgDao.add(bean)>0?true:false;
        }
        return false;
    }

    @Override
    public MyMsgBean findBeanById(Long id) {
        return myMsgDao.findBeanById(id)==null?null:myMsgDao.findBeanById(id);
    }

    @Override
    public List<MyMsgBean> findAll() {
        return myMsgDao.findAll()==null?null:(myMsgDao.findAll().size()<1?null:myMsgDao.findAll());
    }
}
