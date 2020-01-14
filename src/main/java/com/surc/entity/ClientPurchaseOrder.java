package com.surc.entity;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ClientPurchaseOrder extends ItemOrder {
    private long orderId;
    private List<Goods> goodsList;
}
