package com.deutsche.groups.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WordMatchingResponse {
    private String chatId;
    private String word;
    private int matches;
    private int allWords;
}
