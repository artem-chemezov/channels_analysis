package com.deutsche.view.tools.methods;

import com.deutsche.view.errors.ErrorHandler;
import com.deutsche.view.errors.ErrorHandlerImpl;
import com.deutsche.view.errors.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Function;

public class CategorizeChannel implements Function<List<String>,List<String>> {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ErrorHandler errorHandler = new ErrorHandlerImpl();

    @Override
    public List<String> apply(List<String> params) {
        String userId = params.get(0);
        String chatId = params.get(1);
        String group = params.get(2);
        String amount = "25";
        if (params.size() == 4) {
            amount = params.get(3);
        }

        String query = "http://localhost:9090/classification?";
        query = query + "userId=" + userId + "&chatId=" + chatId + "&group=" + group + "&amount=" + amount;
        System.out.println(query);
        ResponseEntity<Status> answer = restTemplate.getForEntity(query, Status.class);
        System.out.println("answer from operator: " + answer.getBody().message);
        return List.of(errorHandler.handle(answer.getBody()));
    }
}
