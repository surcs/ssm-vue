package com.surc.service.impl;

import com.surc.dao.BaseMapper;
import com.surc.dao.DispatchOrderMapper;
import com.surc.entity.ClientPurchaseOrder;
import com.surc.entity.DispatchOrder;
import com.surc.entity.Goods;
import com.surc.enums.OrderState;
import com.surc.service.DispatchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class DispatchOrderServiceImpl extends BaseServiceImpl<DispatchOrder,Long> implements DispatchOrderService {
    @Autowired
    private DispatchOrderMapper dispatchOrderMapper;

    @Override
    public BaseMapper getMapper() {
        return dispatchOrderMapper;
    }

    @Override
    public void checkedDispatchOrder(Long dispatchOrderId, Long warehouseKeeperId) {
        DispatchOrder dispatchOrder = dispatchOrderMapper.selectById(dispatchOrderId);
        dispatchOrder.setWarehouseKeeperId(warehouseKeeperId);
        dispatchOrder.setState(OrderState.CLOSE);
        dispatchOrderMapper.update(dispatchOrder);
    }

    @Override
    public List<DispatchOrder> selectSalesKeeperUncheck() {
        return dispatchOrderMapper.selectSalesKeeperUncheck();
    }

    @Override
    public List<DispatchOrder> selectWarehouseKeeperUncheck() {
        return dispatchOrderMapper.selectWarehouseKeeperUncheck();
    }

    @Override
    public void createByClientPurchaseOrder(ClientPurchaseOrder clientPurchaseOrder) {
        DispatchOrder dispatchOrder = (DispatchOrder) applicationContext.getBean("dispatchOrder");
        for (Goods goods:clientPurchaseOrder.getGoodsList()
             ) {
            dispatchOrder.setState(OrderState.S_UNCHECK);
            dispatchOrder.setNumber(goods.getPaid());
            dispatchOrder.setGoodsId(goods.getGoodsId());
            dispatchOrder.setOrderId(clientPurchaseOrder.getOrderId());
            dispatchOrder.setAddress(clientPurchaseOrder.getAddress());
            dispatchOrder.setDate(clientPurchaseOrder.getDate());
            dispatchOrder.setSum(goods.getPrice() * goods.getPaid());
            dispatchOrder.setClientPurchaseOrderId(clientPurchaseOrder.getOrderId());
            dispatchOrderMapper.insert(dispatchOrder);
        }
    }
}
