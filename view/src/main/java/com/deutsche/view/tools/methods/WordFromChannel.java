package com.deutsche.view.tools.methods;

import com.deutsche.view.errors.ErrorHandler;
import com.deutsche.view.errors.ErrorHandlerImpl;
import com.deutsche.view.models.VkRepetition;
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
        String word =  params.get(1);
        String group = params.get(2);
        String amount = params.get(3);


        String query = "http://localhost:9090/posts?";
        query = query + "userId=" + userId + "&word=" + word + "&group=" + group + "&amount=" + amount;
        ResponseEntity<VkRepetition> answer = restTemplate.getForEntity(query, VkRepetition.class);
        String message = answer.getBody().toString();
        return List.of(message);
    }
}
