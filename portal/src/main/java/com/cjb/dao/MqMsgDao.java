package com.cjb.dao;

import com.cjb.bean.MqMsg;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MqMsgDao {

    int insert(MqMsg mqMsg);

    int updateById(MqMsg mqMsg);
}