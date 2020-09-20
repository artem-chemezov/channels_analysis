package com.deutsche.view.commands.functions;

import org.json.JSONObject;

import java.util.function.Function;

public class Repetitions implements Function<JSONObject, String> {
    @Override
    public String apply(JSONObject receivedMessage) {
        String returnMessage = "";
        returnMessage += "Всего слов: " + receivedMessage.get("allWords") + "\n";
        returnMessage += "Совпадений: " + receivedMessage.get("matches") + "\n";
        returnMessage += "Процентов: " + (double)((int)(Double.parseDouble(receivedMessage.get("matches").toString()) / Double.parseDouble(receivedMessage.get("allWords").toString()) * 10000)) / 100 + "%";
        return returnMessage;
    }
}
