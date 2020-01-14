package com.surc.service;

import com.surc.entity.DispatchOrder;
import com.surc.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService extends BaseService<PurchaseOrder,Long> {
    void insertPurchaseOrderByIdNumber(Long id, int number);
    void checkedPurchaseOrder(Long purchaseOrderId,Long warehouseKeeperId);
    List<DispatchOrder> selectToClose();
    List<DispatchOrder> selectToConfirm();
    void createById(Long id);
}
