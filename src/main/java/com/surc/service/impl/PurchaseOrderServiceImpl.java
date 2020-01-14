package com.surc.service.impl;

import com.surc.dao.BaseMapper;
import com.surc.dao.GoodsMapper;
import com.surc.dao.PurchaseOrderMapper;
import com.surc.entity.DispatchOrder;
import com.surc.entity.Goods;
import com.surc.entity.PurchaseOrder;
import com.surc.enums.OrderState;
import com.surc.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class PurchaseOrderServiceImpl extends BaseServiceImpl<PurchaseOrder,Long> implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public BaseMapper getMapper() {
        return purchaseOrderMapper;
    }

    @Override
    public void insertPurchaseOrderByIdNumber(Long id, int number) {
        Goods goods = goodsMapper.selectById(id);
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setGoodsId(id);
        purchaseOrder.setSum(goods.getPrice() * number);
        purchaseOrder.setState(OrderState.UNCHECK);
        purchaseOrder.setAddress("Address");
        purchaseOrder.setDate(new Date());
    }

    @Override
    public void checkedPurchaseOrder(Long purchaseOrderId, Long warehouseKeeperId) {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectById(purchaseOrderId);
        purchaseOrder.setState(OrderState.CLOSE);

    }

    @Override
    public List<DispatchOrder> selectToClose() {
        return purchaseOrderMapper.selectToClose();
    }

    @Override
    public List<DispatchOrder> selectToConfirm() {
        return purchaseOrderMapper.selectToConfirm();
    }

    @Override
    public void createById(Long id) {
        PurchaseOrder purchaseOrder = (PurchaseOrder) applicationContext.getBean("purchaseOrder");
        purchaseOrder.setDate(new Date());
        purchaseOrder.setGoodsId(id);
        purchaseOrder.setState(OrderState.W_UNCHECK);
        purchaseOrder.setAddress("ADDRESS");
        purchaseOrderMapper.insert(purchaseOrder);
    }
}
