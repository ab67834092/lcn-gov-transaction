package com.cjb.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WarehouseDao {

    public void test(@Param("userName") String userName);
}
