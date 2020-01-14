package com.surc.service;

import com.surc.entity.ClientPurchaseOrder;
import com.surc.entity.DispatchOrder;

import java.util.List;

public interface DispatchOrderService extends BaseService<DispatchOrder,Long> {
    void checkedDispatchOrder(Long dispatchOrderId, Long warehouseKeeperId);
    List<DispatchOrder> selectSalesKeeperUncheck();
    List<DispatchOrder> selectWarehouseKeeperUncheck();
    void createByClientPurchaseOrder(ClientPurchaseOrder clientPurchaseOrder);
}
