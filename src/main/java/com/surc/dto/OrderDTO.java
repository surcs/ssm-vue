package com.surc.dto;

import com.surc.entity.ClientPurchaseOrder;
import com.surc.entity.DispatchOrder;
import com.surc.entity.Goods;
import com.surc.enums.OrderState;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
//
//    private long Id;
//    private String address;
//    private OrderState state;
//    private float sum;
//    private String clientEmail;
//    private long salesmanId;
//    private List<Goods> goodsList;
}
