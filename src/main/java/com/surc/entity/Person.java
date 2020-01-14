package com.surc.entity;

import com.surc.enums.Sex;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String email;
    private String name;
    private String phone;
    private Sex sex;
    private Date birth;
}
