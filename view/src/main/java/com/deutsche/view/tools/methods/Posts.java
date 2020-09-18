package com.deutsche.view.tools.methods;

import com.deutsche.view.errors.ErrorHandler;
import com.deutsche.view.errors.ErrorHandlerImpl;
import com.deutsche.view.models.VkData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Posts  implements Function<List<String>,List<String>> {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ErrorHandler errorHandler = new ErrorHandlerImpl();


    @Override
    public List<String> apply(List<String> params) {
        String userId = params.get(0);
        String group = params.get(1);
        String amount = params.get(2);

        String query = "http://localhost:9090/posts?";
        query = query + "userId=" + userId + "&group=" + params.get(1) + "&amount=" + params.get(2);
        System.out.println("url bot: " + query);
        ResponseEntity<VkData[]> answer = restTemplate.getForEntity(query, VkData[].class);
        List<String> messages = new java.util.ArrayList<>(List.of("Ищем по группе: " + group
                + '\n' + " по количеству постов: " + amount
                + '\n' + '\n' + "Результат: " + '\n'));
        Arrays.stream(answer.getBody()).forEach(value -> messages.add(value.toString()));

        return messages;
    }
}
