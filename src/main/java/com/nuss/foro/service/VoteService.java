package com.nuss.foro.service;

import com.nuss.foro.dto.VoteDto;
import com.nuss.foro.exception.PostNotFoundException;
import com.nuss.foro.exception.SpringRedditException;
import com.nuss.foro.model.Post;
import com.nuss.foro.model.Vote;
import com.nuss.foro.repository.PostRepository;
import com.nuss.foro.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.nuss.foro.model.VoteType.*;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    public void vote(VoteDto voteDto){
            Post post = postRepository.findById(voteDto.getPostId()).orElseThrow(()->new PostNotFoundException("Post Not Found with ID -"+voteDto.getPostId()));
            Optional<Vote> voteByPostAndUser=voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,authService.getCurrentUser());
            if(voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())){
                throw new SpringRedditException("You have already "+voteDto.getVoteType()+" 'd for this post");
            }
            if(UPVOTE.equals(voteDto.getVoteType())){
                post.setVoteCount(post.getVoteCount()+1);
            }else{
                post.setVoteCount(post.getVoteCount()-1);
            }
            voteRepository.save(mapToVote(voteDto,post));
            postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post){
        return Vote.builder().voteType(voteDto.getVoteType()).post(post).user(authService.getCurrentUser()).build();
    }
}
