package com.surc.entity;

import com.surc.enums.Sex;
import com.surc.enums.UserPosition;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class User extends Person{
    private long id;
    private String password;
    private UserPosition position;

    public User(String email, String name, String phone, Sex sex, Date birth, long id, String password, UserPosition position) {
        super(email, name, phone, sex, birth);
        this.id = id;
        this.password = password;
        this.position = position;
    }
}
