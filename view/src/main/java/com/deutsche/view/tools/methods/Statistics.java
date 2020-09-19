package com.deutsche.view.tools.methods;

import java.util.List;
import java.util.function.Function;

public class Statistics implements Function<List<String>,List<String>> {
    @Override
    public List<String> apply(List<String> params) {
        return List.of("Немного статистики по каналу " + params.get(0) + " в период " + params.get(1));
    }
}
