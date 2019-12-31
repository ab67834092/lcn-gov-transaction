package com.cjb.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cjb.api.WarehouseApi;
import com.cjb.service.WarehouseService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = "1.0.0")
public class WarehouseApiImpl implements WarehouseApi {

    @Autowired
    WarehouseService warehouseService;

    @Override
    @LcnTransaction
    public void test() {
        warehouseService.test();
    }
}
