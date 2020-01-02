package com.cjb.service;

import com.cjb.dao.MsgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MsgServiceImpl implements MsgService {

    @Autowired
    MsgDao msgDao;

    @Override
    public void addMsg(){
//        try{
//            int i = 1/0;
            msgDao.addMsg(UUID.randomUUID().toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
