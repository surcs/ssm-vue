package com.surc.service.impl;

import com.surc.BaseTest;
import com.surc.dao.ClientPurchaseOrderMapper;
import com.surc.entity.ClientPurchaseOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientPurchaseOrderServiceImplTest extends BaseTest {
    @Autowired
    ClientPurchaseOrderMapper clientPurchaseOrderMapper;

    @Test
    public void countSalesByGoodsIdTest(){
        float sum = 0;
        long id = 1;
        List<ClientPurchaseOrder> clientPurchaseOrders = clientPurchaseOrderMapper.selectCountAllSales("2019-12-12","2019-12-19");
        System.out.println(clientPurchaseOrders);
        System.out.println(sum);
    }

    public static String getType(Object o){ //获取变量类型方法
        return o.getClass().toString(); //使用int类型的getClass()方法
    }
}
