package com.deutsche.view.handlers;

import com.deutsche.view.tools.Buttons;
import com.deutsche.view.tools.Keyboard;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.*;
import java.util.stream.Stream;

@Component
@NoArgsConstructor
public class ResponseHandler implements Handler{
    //private Buttons state;
    @Autowired
    private Keyboard keyboard;
    private String message;
    private Map<Long, Buttons> state = new HashMap();

    public List<SendMessage> handle(Update update){
        message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        if (message.startsWith("/start")){
            SendMessage resultMessage = new SendMessage();
            keyboard.setButtons(resultMessage);
            resultMessage.setText("Choose your option!");
            state.put(chatId, Buttons.DEFAULT);
            return List.of(resultMessage);
        }

        if (Stream.of(Buttons.values()).anyMatch(value -> value.label.equals(message))){
            SendMessage resultMessage = new SendMessage();
            Optional<Buttons> option = Stream.of(Buttons.values()).filter(value -> value.label.equals(message)).findFirst();
            String result = option.orElse(Buttons.DEFAULT).requiredParams;
            state.put(chatId, option.orElse(Buttons.DEFAULT));
            keyboard.setEmpty(resultMessage);
            resultMessage.setText(result);
            return List.of(resultMessage);
        }

        List<String> params = new ArrayList<>();
        params.add(update.getMessage().getFrom().getId().toString());
        params.addAll(Arrays.asList(message.split(";")));
        List<String> result = state.get(chatId).onClick.apply(params);
        List<SendMessage> messages = new ArrayList<>();
        result.forEach(text -> messages.add(new SendMessage().setText(text)));
        state.put(chatId, Buttons.DEFAULT);
        return messages;
    }
}
