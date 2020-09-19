package com.deutsche.operator.services;

import com.deutsche.operator.enums.GroupsDefinition;
import com.deutsche.operator.enums.Status;

public interface CheckConditionsService {
    Status checkUser(int userId);
    GroupsDefinition defineGroupDatabase(int groupId);
}
