package com.deutsche.view.errors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Errors {

    ISNOTPAID(-1, "Вы не оплатили подписку"),
    UNKNOWNUSER(-2, "Мы не нашли Ваш аккаунт"),
    UNKNOWN(-20, "Неизвестная ошибка");


    public int code;
    public String message;

}
