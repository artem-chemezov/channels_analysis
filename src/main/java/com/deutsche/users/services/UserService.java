package com.deutsche.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.deutsche.users.model.User;
import com.deutsche.users.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user){
        userRepo.save(user);
    }

    public User getByLogin(String login){
        return userRepo.findByLogin(login);
    }


}
