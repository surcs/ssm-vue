package com.surc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Sex{
    MALE("MALE"),
    FEMALE("FEMALE");

    private String value;

    @JsonCreator
    public static Sex fromString(String string){
        for (Sex sex:values()
             ) {
            if (sex.getValue().equals(string)) return sex;
        }
        throw new IllegalArgumentException();
    }
}
