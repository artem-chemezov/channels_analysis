package com.deutsche.view.tools.methods;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Statistics implements Function<List<String>,String> {
    @Override
    public String apply(List<String> params) {
        return "Немного статистики по каналу " + params.get(0) + " в период " + params.get(1);
    }
}
