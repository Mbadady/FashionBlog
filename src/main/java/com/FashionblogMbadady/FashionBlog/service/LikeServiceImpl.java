package com.FashionblogMbadady.FashionBlog.service;

import com.FashionblogMbadady.FashionBlog.customException.BlogApiException;
import com.FashionblogMbadady.FashionBlog.customException.ResourceNotFoundException;
import com.FashionblogMbadady.FashionBlog.dto.request.CreateLikeRequest;
import com.FashionblogMbadady.FashionBlog.entity.Like;
import com.FashionblogMbadady.FashionBlog.entity.Post;
import com.FashionblogMbadady.FashionBlog.repository.LikeRepository;
import com.FashionblogMbadady.FashionBlog.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class LikeServiceImpl implements LikeService{

    private PostRepository postRepository;

    private LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(PostRepository postRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public String AddLike(Long postId, CreateLikeRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post", "id", postId));

        Like like = new Like();

        like.setEmail(request.getEmail());
        like.setPost(post);

         likeRepository.save(like);

        return "Post with post id " + postId + " was liked";  
    }


    @Override
    public List<Like> getLikesByPostsId(Long postId) {

//        List<Like> likes = likeRepository.findPostById(postId);
//
//        if(likes.isEmpty()){
//            throw new ResourceNotFoundException("Post does not exist");
//        }
//        return likes;

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));

        Like like = new Like();
        if(!like.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Likes not a match for the posts");
        }
        return likeRepository.findAll();
    }
}
