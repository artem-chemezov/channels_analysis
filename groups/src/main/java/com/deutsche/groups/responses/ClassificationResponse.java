package com.deutsche.groups.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.json.JSONObject;

@Data
@AllArgsConstructor
public class ClassificationResponse {
    private String chatId;
    private String groupId;
    private JSONObject classifications;
}
