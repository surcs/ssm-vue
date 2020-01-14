package com.surc.dao;

import com.surc.BaseCRUD;
import com.surc.BaseTest;
import com.surc.entity.DispatchOrder;
import com.surc.enums.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class DispatchOrderMapperTest extends BaseTest implements BaseCRUD {
    @Autowired
    private DispatchOrderMapper dispatchOrderMapper;
    private DispatchOrder dispatchOrder;

    @BeforeEach
    public void init(){
        dispatchOrder = new DispatchOrder();
        dispatchOrder.setNumber(2);
        dispatchOrder.setOrderId(3);
        dispatchOrder.setGoodsId(4);
        dispatchOrder.setAddress("ad");
        dispatchOrder.setDate(new Date());
        dispatchOrder.setState(OrderState.S_UNCHECK);
    }

    @Test
    @Override
    public void insert() {
        dispatchOrderMapper.insert(dispatchOrder);
    }

    @Test
    @Override
    public void delete() {
        dispatchOrderMapper.delete((long) 1);
    }

    @Test
    @Override
    public void update() {
        dispatchOrder.setId(1);
        dispatchOrder.setState(OrderState.CLOSE);
        dispatchOrderMapper.update(dispatchOrder);
    }

    @Test
    @Override
    public void select() {
        DispatchOrder dispatchOrder1 = dispatchOrderMapper.selectById((long) 1);
        System.out.println(dispatchOrder1.toString());
    }

    @Test
    @Override
    public void selectAll() {
        System.out.println(dispatchOrderMapper.selectAll());
    }
}
