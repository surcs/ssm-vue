package com.surc.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.surc.util.HashKeyDeserializer;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(keyUsing = HashKeyDeserializer.class)
public class Goods {
    private long goodsId;
    private String goodsName;
    private int inventory;
    private int minInventory;
    private float price;
    private int total; //合同总数
    private int paid; //客户已付款数
    private int send; //已发货数

    public Goods(long goodsId, String goodsName, int inventory, int minInventory, float price) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.inventory = inventory;
        this.minInventory = minInventory;
        this.price = price;
    }
}
