package com.deutsche.repo;

import org.springframework.data.repository.CrudRepository;
import com.deutsche.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
