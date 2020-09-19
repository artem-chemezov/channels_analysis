package com.deutsche.operator.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GroupsDefinition {
    SAVED(1, "mongo"),
    UNSAVED(2, "postgres"),
    UNDEFINED(-3, "default");

    public int code;
    public String dbName;
}
