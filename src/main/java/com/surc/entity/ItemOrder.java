package com.surc.entity;

import com.surc.enums.OrderState;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrder {
    private long Id;
    private Date date;
    private String address;
    private OrderState state;
    private float sum;
}
