package com.deutsche.view.tools.methods;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CategorizeChannel implements Function<List<String>,String> {
    @Override
    public String apply(List<String> params) {
        return "Классифицируем канал " + params.get(0);
    }
}
