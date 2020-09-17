package com.deutsche.view.tools.methods;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class WordFromChannel implements Function<List<String>,String> {
    @Override
    public String apply(List<String> params) {
        return"Ты ввел слово " + params.get(0) + ", ищем по каналу " + params.get(1) + " в период " + params.get(2);
    }
}
