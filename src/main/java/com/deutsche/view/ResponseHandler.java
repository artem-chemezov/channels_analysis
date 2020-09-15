package com.deutsche.view;

import com.deutsche.view.tools.Buttons;
import com.deutsche.view.tools.Keyboard;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
@Component
public class ResponseHandler implements Handler{
    private Buttons state;
    private Keyboard keyboard;

    public ResponseHandler() {
        this.keyboard = new Keyboard();
        this.state = Buttons.DEFAULT;
    }

    public SendMessage handle(Update update, List<String> params){
        SendMessage resultMessage = new SendMessage();
        if (update.getMessage().getText().startsWith("/start")){
            keyboard.setButtons(resultMessage);
            resultMessage.setText("Choose your option!");
            return resultMessage;
        }

        if (Stream.of(Buttons.values()).anyMatch(value -> value.label.equals(update.getMessage().getText()))){
            Optional<Buttons> option = Stream.of(Buttons.values()).filter(value -> value.label.equals(update.getMessage().getText())).findFirst();
            String result = option.orElse(Buttons.DEFAULT).requiredParams;
            state = option.orElse(Buttons.DEFAULT);
            keyboard.setEmpty(resultMessage);
            resultMessage.setText(result);
            return resultMessage;
        }

        String result = state.onClick.apply(Arrays.asList(update.getMessage().getText().split(";")));
        resultMessage.setText(result);
        return resultMessage;
    }
}
