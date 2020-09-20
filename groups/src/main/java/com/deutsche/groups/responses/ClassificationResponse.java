package com.deutsche.groups.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ClassificationResponse {
    private String chatId;
    private String groupId;
    Map<String, Double> classifications;
}
