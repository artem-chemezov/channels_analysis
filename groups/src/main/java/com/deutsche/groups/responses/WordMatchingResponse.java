package com.deutsche.groups.responses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonDeserialize
public class WordMatchingResponse {
    private int matches;
    private int allWords;
}
