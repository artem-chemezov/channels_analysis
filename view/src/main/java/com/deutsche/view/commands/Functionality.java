package com.deutsche.view.commands;

import com.deutsche.view.commands.functions.Statistics;
import com.deutsche.view.commands.functions.Repetitions;
import lombok.AllArgsConstructor;
import org.json.JSONObject;

import java.util.function.Function;

@AllArgsConstructor
public enum Functionality {

    REPETITIONS("repetitions", new Repetitions()),
    CLASSIFICATION("classification",  new Statistics());

    Functionality(String name) {
        this.name = name;
    }

    public String name;
    public Function<JSONObject, String> processMessage;
}
