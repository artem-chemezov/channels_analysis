package com.deutsche.view.commands.functions;

import org.json.JSONObject;

import java.util.function.Function;

public class Repetitions implements Function<JSONObject, String> {
    @Override
    public String apply(JSONObject recievedMessage) {
        String returnMessage = "";
        returnMessage += "Всего слов: " + recievedMessage.get("allWords") + "\n";
        returnMessage += "Совпадений: " + recievedMessage.get("matches") + "\n";
        returnMessage += "Процентов: " + (double)((int)(Double.parseDouble(recievedMessage.get("matches").toString()) / Double.parseDouble(recievedMessage.get("allWords").toString()) * 10000)) / 100 + "%";
        return returnMessage;
    }
}
