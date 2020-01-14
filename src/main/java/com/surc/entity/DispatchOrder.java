package com.surc.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class DispatchOrder extends ItemOrder{
    private long goodsId;
    private int number;
    private long orderId;
    private long salesKeeperId;
    private long warehouseKeeperId;
    private long clientPurchaseOrderId;
    private long logisticsId; //物流单号
}
