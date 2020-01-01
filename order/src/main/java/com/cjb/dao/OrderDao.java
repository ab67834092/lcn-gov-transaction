package com.cjb.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    public void test(@Param("orderNo") String orderNo);
}
