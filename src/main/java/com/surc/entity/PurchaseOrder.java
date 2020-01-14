package com.surc.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class PurchaseOrder extends ItemOrder{
    private long goodsId;
    private int number;
    private long warehouseKeeperIdConfirm;
    private long warehouseKeeperIdClose;
    private long logisticsId;
}
