package com.deutsche.view.handlers;

import com.deutsche.view.tools.Buttons;
import com.deutsche.view.tools.Keyboard;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@NoArgsConstructor
public class ResponseHandler implements Handler{
    private Buttons state;
    @Autowired
    private Keyboard keyboard;
    private String message;

    public SendMessage handle(Update update){
        message = update.getMessage().getText();
        SendMessage resultMessage = new SendMessage();

        if (message.startsWith("/start")){
            keyboard.setButtons(resultMessage);
            resultMessage.setText("Choose your option!");
            state = Buttons.DEFAULT;
            return resultMessage;
        }

        if (Stream.of(Buttons.values()).anyMatch(value -> value.label.equals(message))){
            Optional<Buttons> option = Stream.of(Buttons.values()).filter(value -> value.label.equals(message)).findFirst();
            String result = option.orElse(Buttons.DEFAULT).requiredParams;
            state = option.orElse(Buttons.DEFAULT);
            keyboard.setEmpty(resultMessage);
            resultMessage.setText(result);
            return resultMessage;
        }

        List<String> params = new ArrayList<>();
        params.add(update.getMessage().getFrom().getId().toString());
        params.addAll(Arrays.asList(message.split(";")));
        String result = state.onClick.apply(params);
        resultMessage.setText(result);
        state = Buttons.DEFAULT;
        return resultMessage;
    }

    @PostConstruct
    private void setState(){
        this.state = Buttons.DEFAULT;
    }
}
