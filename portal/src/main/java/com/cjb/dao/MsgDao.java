package com.cjb.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MsgDao {

    public void addMsg(@Param("msgId") String msgId);
}
