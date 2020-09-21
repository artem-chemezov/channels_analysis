package com.deutsche.operator.repo;

import com.deutsche.operator.models.Group;
import com.deutsche.operator.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedPosts extends JpaRepository<Post, Integer> {
    Post findByPostId(String postId);
}
