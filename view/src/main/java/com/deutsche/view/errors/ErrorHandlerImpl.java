package com.deutsche.view.errors;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;


@Component
public class ErrorHandlerImpl implements ErrorHandler {
    @Override
    public String handle(String msg, int code) {
        if (code < 0){
            Optional<Errors> error = Arrays.stream(Errors.values()).filter(value -> value.code == code).findFirst();
            return error.orElse(Errors.UNKNOWN).message;
        }
        return msg;
    }
}
