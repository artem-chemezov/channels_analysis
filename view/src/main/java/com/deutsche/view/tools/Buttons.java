package com.deutsche.view.tools;

import com.deutsche.view.tools.methods.CategorizeChannel;
import com.deutsche.view.tools.methods.Statistics;
import com.deutsche.view.tools.methods.WordFromChannel;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@AllArgsConstructor
public enum Buttons {
    DEFAULT("default", "", (params) -> ("Мы не знаем таких команд :(")),
    CHANNEL("Классификация канала", "Введи канал/группу в формате @testChannel", new CategorizeChannel()),
    WORD("Поиск слова на каннале","Введи слово, канал/группу, дату с, дату по в формате Кино; @testChannel; 26.06.2017 20:40 - 18.07.2019 06:45", new WordFromChannel()),
    STATISTICS("Статистика", "Введи канал/группу, дату с, дату по в формате @testChannel; 26.06.2017 20:40 - 18.07.2019 06:45", new Statistics());

    public String label;
    public String requiredParams;
    public Function<JSONObject,String> onClick;
}
