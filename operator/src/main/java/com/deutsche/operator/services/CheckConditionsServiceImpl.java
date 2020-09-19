package com.deutsche.operator.services;

import com.deutsche.operator.enums.Status;
import com.deutsche.operator.enums.GroupsDefinition;
import com.deutsche.operator.models.User;
import com.deutsche.operator.repo.GroupsRepo;
import com.deutsche.operator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckConditionsServiceImpl implements CheckConditionsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private GroupsRepo groupRepo;
    @Autowired
    private RestService restService;
    @Override
    public Status checkUser(int userId) {
        userRepo.save(new User(userId, true));
        User user = userRepo.findByUserId(userId);
        if (user == null) {
            return Status.UNKNOWNUSER;
        }
        if (!user.isPaid()){
            return Status.ISNOTPAIDUSER;
        }
        return Status.DEFAULT;
    }

    @Override
    public GroupsDefinition defineGroupDatabase(int groupId) {
        if (groupId == -1){
            return GroupsDefinition.UNDEFINED;
        }
        if (groupRepo.findById(groupId) == null) {
            return GroupsDefinition.SAVED;
        }
        return GroupsDefinition.UNSAVED;
    }
}
