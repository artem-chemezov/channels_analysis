package com.deutsche.view.commands.functions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Statistics implements Function<JSONObject, String> {

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
        definePercentage *= 100;
        returnMessage = "";
        returnMessage += "Определенная категория: " + defineTheme + "\nПроцент: " + definePercentage.toString().substring(0,5) + "%\n\n";
        returnMessage += "Подробнее:\n";
        for (Map.Entry<String, Double> entry: analysis.entrySet()) {
            Double percentage = entry.getValue() * 100;
            String persString = percentage.toString();
            if (persString.length() > 5){
                persString = percentage.toString().substring(0,5);
            }
            returnMessage += entry.getKey() + ": " + persString + "% (" + ((JSONObject) classifications.get(entry.getKey())).get("clueWords").toString() + "/" + ((JSONObject) classifications.get(entry.getKey())).get("allWords").toString() +")\n";
        }
        return returnMessage;
    }
}
