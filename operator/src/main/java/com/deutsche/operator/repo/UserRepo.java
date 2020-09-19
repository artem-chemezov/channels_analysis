package com.deutsche.operator.repo;

import com.deutsche.operator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    User findByUserId(int userId);
}
