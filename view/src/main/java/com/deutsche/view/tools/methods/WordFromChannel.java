package com.deutsche.view.tools.methods;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

public class WordFromChannel implements Function<JSONObject,String> {
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String apply(JSONObject params) {
        String query = "http://localhost:8080/repetitions?";
        query = query + "userId" + params.get("userId") + "&word=" + params.get("word") + "&group=" + params.get("group") + "&amount=" + params.get("amount");
        int answer = restTemplate.getForObject(query, int.class);
        return "Ты ввел слово: " + params.get("word")
                + '\n' + ", ищем по группе: " + params.get("group")
                + '\n' + " по количеству сообщений: " + params.get("amount")
                + '\n' + '\n' + "Результат = " + answer;
    }
}
