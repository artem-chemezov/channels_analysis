package com.deutsche.view.tools.methods;

import java.util.List;
import java.util.function.Function;

public class CategorizeChannel implements Function<List<String>,List<String>> {
    @Override
    public List<String> apply(List<String> params) {
        return List.of("Классифицируем канал " + params.get(0));
    }
}
