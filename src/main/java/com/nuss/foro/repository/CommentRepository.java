package com.nuss.foro.repository;

import com.nuss.foro.model.Comment;
import com.nuss.foro.model.Post;
import com.nuss.foro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);
}
