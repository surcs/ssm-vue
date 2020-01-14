package com.surc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum UserPosition {
    SALESMAN("SALESMAN"),
    SALES_KEEPER("SALES_KEEPER"),
    WAREHOUSE_KEEPER("WAREHOUSE_KEEPER");

    private String position;

    @JsonCreator
    public static UserPosition fromString(String string){
        for(UserPosition position : values()){
            if (position.getPosition().equals(string)) return position;
        }
        throw new IllegalArgumentException();
    }
}
