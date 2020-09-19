package com.deutsche.operator.services;

import com.deutsche.operator.enums.GroupsDefinition;
import com.deutsche.operator.enums.UserErrors;
import com.deutsche.operator.models.Group;

public interface CheckConditionsService {
    UserErrors checkUser(int userId);
    GroupsDefinition defineGroupDatabase(int groupId);
}
