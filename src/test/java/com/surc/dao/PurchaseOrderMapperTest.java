package com.surc.dao;

import com.surc.BaseCRUD;
import com.surc.BaseTest;
import com.surc.entity.PurchaseOrder;
import com.surc.enums.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class PurchaseOrderMapperTest extends BaseTest implements BaseCRUD {
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    private PurchaseOrder purchaseOrder;

    @BeforeEach
    public void init(){
        purchaseOrder = new PurchaseOrder();
        purchaseOrder.setNumber(1);
        purchaseOrder.setGoodsId(4);
        purchaseOrder.setAddress("ad");
        purchaseOrder.setDate(new Date());
        purchaseOrder.setState(OrderState.W_UNCHECK);
    }

    @Test
    @Override
    public void insert() {
        purchaseOrderMapper.insert(purchaseOrder);
    }

    @Test
    @Override
    public void delete() {
        purchaseOrderMapper.delete((long) 1);
    }

    @Test
    @Override
    public void update() {
        purchaseOrder.setId(1);
        purchaseOrder.setState(OrderState.CLOSE);
        purchaseOrderMapper.update(purchaseOrder);
    }

    @Test
    @Override
    public void select() {
        PurchaseOrder purchaseOrder1 = purchaseOrderMapper.selectById((long) 1);
        System.out.println(purchaseOrder1.toString());
    }

    @Test
    @Override
    public void selectAll() {
        System.out.println(purchaseOrderMapper.selectAll());
    }
}
