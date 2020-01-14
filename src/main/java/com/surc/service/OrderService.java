package com.surc.service;

import com.surc.entity.ClientPurchaseOrder;
import com.surc.entity.DispatchOrder;
import com.surc.entity.Order;
import com.surc.enums.OrderState;

import java.util.List;

public interface OrderService extends BaseService<Order,Long> {
    void addClientPurchaseOrder(ClientPurchaseOrder clientPurchaseOrder);
    boolean checkStateWhetherCanModifyById(long id);
    List<Order> selectOrderListBySalesmanId(long id);
    boolean checkCompleteById(long id);
    void closeDispatchOrder(DispatchOrder dispatchOrder);
}
