package com.surc.entity;

import com.surc.enums.Sex;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Client extends Person{
    private List<Order> orders;

    public Client(String email, String name, String phone, Sex sex, Date birth, List<Order> orders) {
        super(email, name, phone, sex, birth);
        this.orders = orders;
    }
}
