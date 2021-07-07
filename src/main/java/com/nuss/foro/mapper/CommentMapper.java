package com.nuss.foro.mapper;

import com.nuss.foro.dto.CommentsDto;
import com.nuss.foro.model.Comment;
import com.nuss.foro.model.Post;
import com.nuss.foro.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel =  "spring")
public interface CommentMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentsDto mapToDto(Comment comment);

}
