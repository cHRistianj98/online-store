package com.christianj98.online_store;

import lombok.Getter;

@Getter
public enum VerificationType {
    ACCOUNT("account");

    private final String name;

    VerificationType(String name) {
        this.name = name;
    }
}
