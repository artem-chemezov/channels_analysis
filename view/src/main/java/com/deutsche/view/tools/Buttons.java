package com.deutsche.view.tools;

import com.deutsche.view.tools.methods.CategorizeChannel;
import com.deutsche.view.tools.methods.Posts;
import com.deutsche.view.tools.methods.WordFromChannel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@RequiredArgsConstructor
public enum Buttons {




    DEFAULT("default", "", (params) -> List.of("Мы не знаем таких команд :(")),
    CHANNEL("Классификация канала", "Введите id группы", new CategorizeChannel()),
    WORD("Поиск слова в группе","Введите слово, id группы, количество постов для поиска в формате Кино,groupName,20", new WordFromChannel()),
    //STATISTICS("Статистика", "Введи канал/группу, дату с, дату по в формате @testChannel; 26.06.2017 20:40 - 18.07.2019 06:45", new Statistics()),
    POSTS("Почитать посты", "Введите id группы, количество постов в формате: groupId,50", new Posts());

    public static String examples = "Примеры групп:\n\n" +
            "Типичный программист: tproger\n" +
            "Хабрахабр: habrfun\n" +
            "Лентач: lentach";
    public String label;
    public String requiredParams;
    public Function<List<String>,List<String>> onClick;
}
