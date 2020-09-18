package com.deutsche.operator.repo;

import com.deutsche.operator.models.Group;
import com.deutsche.operator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepo extends JpaRepository<Group, Integer> {
    Group findById(int id);
}
