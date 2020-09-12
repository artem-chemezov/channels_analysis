package com.deutsche.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.deutsche.model.User;
import com.deutsche.repo.UserRepo;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void saveUser(User user){
        user.setLastVisit(LocalDateTime.now());
        userRepo.save(user);
    }

    public User getByLogin(String login){
        return userRepo.findByLogin(login);
    }


}
