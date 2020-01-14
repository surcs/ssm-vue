package com.surc.service;

import com.surc.entity.DispatchOrder;
import com.surc.entity.Goods;
import com.surc.entity.PurchaseOrder;

public interface GoodsService extends BaseService<Goods,Long> {
    boolean checkEnoughById(long id);
    boolean checkEnoughToSentById(long id,int number);
    void addInventory(PurchaseOrder purchaseOrder);
    void closeDispatchOrder(DispatchOrder dispatchOrder);
}
