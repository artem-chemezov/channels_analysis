package com.deutsche.groups.services;

import org.json.JSONObject;

import java.util.List;

public interface GroupService {
    public List<JSONObject> getPosts(String name, int amount);
}
