package com.deutsche.operator.services;

import com.deutsche.operator.models.User;
import com.deutsche.operator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TelegramCommandsHandler implements CommandsHandler {
    @Autowired
    private UserRepo repo;

    @Override
    public int getWordRepetitions(int userId, String word, String group, int amount) {
        //repo.save(new User(userId, true));
        User user = repo.findByUserId(userId);
        if (user == null) {
            return -2;
        }
        if (!user.isPaid()){
            return -1;
        }
        return 10;
    }

    @Override
    public String getGroupClass(String group) {
        return null;
    }

    @Override
    public List<String> getStatistics(String group) {
        return null;
    }
}
