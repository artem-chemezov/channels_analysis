package com.deutsche.operator.repo;

import com.deutsche.operator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>{

}
