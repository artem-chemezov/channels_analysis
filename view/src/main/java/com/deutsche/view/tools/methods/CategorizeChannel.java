package com.deutsche.view.tools.methods;

import org.json.JSONObject;

import java.util.function.Function;

public class CategorizeChannel implements Function<JSONObject,String> {
    @Override
    public String apply(JSONObject params) {
        return "Классифицируем канал " + params.get("group");
    }
}
