package com.deutsche.operator.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    ADDEDTOQUEUE(1),
    DEFAULT(0),
    ISNOTPAIDUSER(-1),
    UNKNOWNUSER(-2),
    CONNECTIONERROR(-3);

    public int code;
}
