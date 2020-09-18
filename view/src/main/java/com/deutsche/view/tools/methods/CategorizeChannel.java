package com.deutsche.view.tools.methods;

import java.util.List;
import java.util.function.Function;

public class CategorizeChannel implements Function<List<String>,String> {
    @Override
    public String apply(List<String> params) {
        return "Классифицируем канал " + params.get(0);
    }
}
