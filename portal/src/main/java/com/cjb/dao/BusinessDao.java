package com.cjb.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BusinessDao {

    public void insert(@Param("businessId") String businessId);
}
