package com.deutsche.view.commands.functions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Classification implements Function<JSONObject, String> {

    @Override
    public String apply(JSONObject receivedMessage) {
        String returnMessage = "";
        System.out.println(receivedMessage.toString());
        Map<String,Double> analysis = new HashMap<>();
        JSONObject classifications = (JSONObject) receivedMessage.get("classifications");
        String defineTheme = "";
        Double definePercentage = 0.0;
        for (var theme : classifications.keySet()){
            Double percentage = Double.parseDouble(((JSONObject) classifications.get(theme)).get("clueWords").toString()) / Double.parseDouble(((JSONObject) classifications.get(theme)).get("allWords").toString());
            if (definePercentage < percentage){
                definePercentage = percentage;
                defineTheme = theme;
            }
            analysis.put(theme, percentage);
        }
        returnMessage += "Определенная категория: " + defineTheme;
        return returnMessage;
    }
}
