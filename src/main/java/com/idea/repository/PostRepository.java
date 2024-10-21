package com.idea.repository;

import com.idea.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostRepository extends JpaRepository<Post,Long> {
}
