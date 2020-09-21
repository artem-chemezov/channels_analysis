package com.deutsche.view.tools;

import com.deutsche.view.tools.methods.Posts;
import com.deutsche.view.tools.methods.Statistics;
import com.deutsche.view.tools.methods.WordFromChannel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@RequiredArgsConstructor
public enum Buttons {




    DEFAULT("default", "", (params) -> List.of("Мы не знаем таких команд :(")),
    //CHANNEL("Классификация канала", "Введите id группы, количество постов для обработки (необязательно)", new Classification()),
    WORD("Поиск слова в группе","Введите слово, id группы, количество постов для поиска в формате Кино,groupName,20", new WordFromChannel()),
    STATISTICS("Статистика", "Введите id группы, количество постов для обработки (необязательно)", new Statistics()),
    POSTS("Почитать посты", "Введите id группы, количество постов в формате: groupId,50", new Posts());

    public static String examples = "\nПримеры групп:\n" +
            "Типичный программист: tproger\n" +
            "Хабрахабр: habrfun\n" +
            "Лентач: lentach";
    public String label;
    public String requiredParams;
    public Function<List<String>,List<String>> onClick;
}
