package com.surc.entity;

import com.surc.enums.OrderState;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Order extends ItemOrder{
    private String clientEmail;
    private long salesmanId;
    private List<Goods> goodsList;
    private List<ClientPurchaseOrder> clientPurchaseOrders;
    private List<DispatchOrder> dispatchOrders;

    public Order(long Id, Date date, String address, OrderState state, float sum, String clientEmail, long salesmanId, List<Goods> goodsList, List<ClientPurchaseOrder> clientPurchaseOrders, List<DispatchOrder> dispatchOrders) {
        super(Id, date, address, state, sum);
        this.clientEmail = clientEmail;
        this.salesmanId = salesmanId;
        this.goodsList = goodsList;
        this.clientPurchaseOrders = clientPurchaseOrders;
        this.dispatchOrders = dispatchOrders;
    }
}
