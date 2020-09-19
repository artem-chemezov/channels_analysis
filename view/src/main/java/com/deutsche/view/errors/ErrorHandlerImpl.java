package com.deutsche.view.errors;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;


@Component
public class ErrorHandlerImpl implements ErrorHandler {
    @Override
    public String handle(Status status) {
        Optional<Status> resultStatus = Arrays.stream(Status.values()).filter(value -> value.code == status.code).findFirst();
        return resultStatus.orElse(Status.UNKNOWNERROR).message;
    }
}
