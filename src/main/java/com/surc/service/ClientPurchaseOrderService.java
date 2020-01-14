package com.surc.service;

import com.surc.entity.ClientPurchaseOrder;

public interface ClientPurchaseOrderService extends BaseService<ClientPurchaseOrder,Long> {
    float countAllSales(String start,String end);
    float countSalesByClientId(String id,String start,String end);
    float countSalesByGoodsId(long id,String start,String end);
    float countSalesBySalesmanId(long id,String start,String end);
    void  sentOutByClientPurchaseOrderId(long id);
    void closeDispatchOrder(long id);
}
