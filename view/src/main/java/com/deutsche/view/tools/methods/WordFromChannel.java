package com.deutsche.view.tools.methods;

import com.deutsche.view.errors.ErrorHandler;
import com.deutsche.view.errors.ErrorHandlerImpl;
import com.deutsche.view.errors.Status;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Function;

@Component
@NoArgsConstructor
public class WordFromChannel implements Function<List<String>,List<String>> {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ErrorHandler errorHandler = new ErrorHandlerImpl();

    @Override
    public List<String> apply(List<String> params) {

        String userId = params.get(0);
        String chatId = params.get(1);
        String word =  params.get(2);
        String group = params.get(3);
        String amount = params.get(4);


        String query = "http://localhost:9090/repetitions?";
        query = query + "userId=" + userId + "&chatId=" + chatId + "&word=" + word + "&group=" + group + "&amount=" + amount;
        System.out.println(query);
        ResponseEntity<Status> answer = restTemplate.getForEntity(query, Status.class);
        System.out.println("answer from operator: " + answer.getBody().message);
        return List.of(errorHandler.handle(answer.getBody()));
    }
}
