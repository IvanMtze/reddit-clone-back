package com.nuss.foro.repository;

import com.nuss.foro.model.Post;
import com.nuss.foro.model.Subreddit;
import com.nuss.foro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
}
