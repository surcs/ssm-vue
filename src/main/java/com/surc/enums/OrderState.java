package com.surc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum OrderState {
    UNPAID("UNPAID"),
    PAID("PAID"),
    RUN("RUN"),
    CLOSE("CLOSE"),
    UNCHECK("UNCHECK"),
    S_UNCHECK("S_UNCHECK"),
    W_UNCHECK("W_UNCHECK");


    private String state;

    @JsonCreator
    public static OrderState fromString(String string){
        for (OrderState orderState:values()
        ) {
            if (orderState.getState().equals(string)) return orderState;
        }
        throw new IllegalArgumentException();
    }
}
