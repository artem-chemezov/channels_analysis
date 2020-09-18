package com.deutsche.operator.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserErrors {
    DEFAULT(1),
    ISNOTPAIDUSER(-1),
    UNKNOWNUSER(-2);

    public int code;
}
