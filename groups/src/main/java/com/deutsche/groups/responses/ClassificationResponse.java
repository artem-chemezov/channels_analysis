package com.deutsche.groups.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClassificationResponse {
    private String chatId;
    private String groupId;
    List<String> classifications;
}
