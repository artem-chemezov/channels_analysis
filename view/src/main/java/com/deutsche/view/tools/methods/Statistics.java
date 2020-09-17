package com.deutsche.view.tools.methods;

import org.json.JSONObject;

import java.util.function.Function;

public class Statistics implements Function<JSONObject,String> {
    @Override
    public String apply(JSONObject params) {
        return "Немного статистики по каналу " + params.get("group") + " в период " + params.get("period");
    }
}
