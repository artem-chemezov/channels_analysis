package com.deutsche.users.repo;

import org.springframework.data.repository.CrudRepository;
import com.deutsche.users.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
