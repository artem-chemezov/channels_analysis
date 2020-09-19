package com.deutsche.view.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VkRepetition {
    private String word;
    private String group;
    private int postsAmount;
    private int wordsAmount;
    private int matches;

    @Override
    public String toString(){
        return "Слово: " + word + "/n" +
                "группа: " + group + "/n" +
                "постов: " + postsAmount + "/n" +
                "слов: " + wordsAmount;
    }
}
