package com.deutsche.view.tools.methods;

import com.deutsche.view.errors.ErrorHandler;
import com.deutsche.view.errors.ErrorHandlerImpl;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Function;

public class Posts  implements Function<List<String>,String> {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ErrorHandler errorHandler = new ErrorHandlerImpl();


    @Override
    public String apply(List<String> params) {
        String userId = params.get(0);
        String group = params.get(1);
        String amount = params.get(2);

        String query = "http://localhost:9090/posts?";
        query = query + "userId=" + userId + "&group=" + params.get(1) + "&amount=" + params.get(2);
        String answer = restTemplate.getForObject(query, JSONObject.class).toString();
        String message = "Ищем по группе: " + group
                + '\n' + " по количеству постов: " + amount
                + '\n' + '\n' + "Результат = " + answer;

        return answer;
    }
}
