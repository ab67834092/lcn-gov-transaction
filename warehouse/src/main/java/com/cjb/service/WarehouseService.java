package com.cjb.service;

import com.cjb.dao.WarehouseDao;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WarehouseService {

    @Autowired
    WarehouseDao warehouseDao;

    @LcnTransaction
    @Transactional
    public void test(){
        //扣减库存
        warehouseDao.test();
    }
}
