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
        returnMessage = "";
        returnMessage += "Определенная категория: " + defineTheme + ", процент: " + definePercentage.toString().substring(0,5) + "\n\n";
        returnMessage += "Подробнее:\n";
        for (Map.Entry<String, Double> entry: analysis.entrySet()) {
            returnMessage += entry.getKey() + ": " + entry.getValue().toString().substring(0,5)+"\n";
        }

//        returnMessage += "Всего слов: " + receivedMessage.get("allWords") + "\n";
//        returnMessage += "Совпадений: " + receivedMessage.get("matches") + "\n";
//        returnMessage += "Процентов: " + (double)((int)(Double.parseDouble(receivedMessage.get("matches").toString()) / Double.parseDouble(receivedMessage.get("allWords").toString()) * 10000)) / 100 + "%";
        return returnMessage;
    }
}
