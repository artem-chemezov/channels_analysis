package com.deutsche.view.errors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    ADDEDTOQUEUE(1, "Мы обрабатываем посты, пожалуйста, подождите..."),
    DEFAULT(0, ""),
    ISNOTPAIDUSER(-1, "Вы не оплатили подписку"),
    UNKNOWNUSER(-2, "Мы не нашли Ваш аккаунт"),
    CONNECTIONERROR(-3, "Во время запроса произошла ошибка"),
    UNKNOWNERROR(-20, "Неизвестная ошибка");

    public int code;
    public String message;
}
