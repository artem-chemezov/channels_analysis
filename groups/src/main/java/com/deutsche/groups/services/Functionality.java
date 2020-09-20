package com.deutsche.groups.services;

import lombok.Getter;

public enum Functionality {
    REPETITIONS("repetitions"), CLASSIFICATION("classification");

    Functionality(String name) {
        this.name = name;
    }

    @Getter
    public String name;
}
