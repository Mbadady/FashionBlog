package com.FashionblogMbadady.FashionBlog.service;

import com.FashionblogMbadady.FashionBlog.customException.BlogApiException;
import com.FashionblogMbadady.FashionBlog.customException.ResourceNotFoundException;
import com.FashionblogMbadady.FashionBlog.dto.request.CreateCommentRequest;
import com.FashionblogMbadady.FashionBlog.entity.Comment;
import com.FashionblogMbadady.FashionBlog.entity.Post;
import com.FashionblogMbadady.FashionBlog.repository.CommentRepository;
import com.FashionblogMbadady.FashionBlog.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CreateCommentRequest createComment(Long postId, CreateCommentRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));

        Comment comment = new Comment();

//        comment.setCreateAt(request.getCreateAt());
        comment.setEmail(request.getEmail());
        comment.setComments(request.getComments());
        comment.setTitle(request.getTitle());
        comment.setPost(post);
//        post.setComments(List.of(comment));

        Comment newComment = commentRepository.save(comment);

        request.setEmail(newComment.getEmail());
        request.setTitle(newComment.getTitle());
        request.setCreateAt(newComment.getCreateAt());
        request.setComments(newComment.getComments());
//        request.setPost(post);
        request.setId(newComment.getId());


        return request;
    }

    @Override
    public CreateCommentRequest updateComment(Long postId, Long commentId, CreateCommentRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));

        Comment commentToUpdate = commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment", "id",commentId));

        commentToUpdate.setUpdatedAt(request.getUpdatedAt());
        commentToUpdate.setId(postId);
        commentToUpdate.setId(commentId);
        commentToUpdate.setComments(request.getComments());
        commentToUpdate.setPost(post);
//        post.setComments(List.of(commentToUpdate));
        Comment updatedComments = commentRepository.save(commentToUpdate);
        request.setUpdatedAt(updatedComments.getUpdatedAt());
        request.setComments(updatedComments.getComments());
        request.setTitle(updatedComments.getTitle());
//        request.setPost(post);
        request.setEmail(updatedComments.getEmail());

        return request;
    }

    @Override
    public String deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));

        Comment commentToUpdate = commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment", "id", commentId));

        commentRepository.deleteById(commentId);

        return "Comment " + commentId + " on post " + postId + " deleted successfully";
    }

    @Override
    public List<CreateCommentRequest> getCommentsByPosts(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post", "id", postId));

        List<Comment> comments = commentRepository.findCommentByPost(post);

        CreateCommentRequest commentRequest = new CreateCommentRequest();

        if(comments.isEmpty()){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comments does not exist");
        }

      return comments.stream().map(comment -> {
            commentRequest.setId(comment.getId());
//            commentRequest.setPost(comment.getPost());
            commentRequest.setTitle(comment.getTitle());
            commentRequest.setEmail(comment.getEmail());
            commentRequest.setComments(comment.getComments());
            commentRequest.setCreateAt(comment.getCreateAt());
            commentRequest.setUpdatedAt(comment.getUpdatedAt());
            return commentRequest;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CreateCommentRequest> findByEmail(String email){

        List<Comment> comments = commentRepository.findByEmail(email);

        if(comments.isEmpty()){
            throw new RuntimeException("Email for the comment not found");
        }

        CreateCommentRequest commentRequest = new CreateCommentRequest();



        return comments.stream().map(comment -> {
            commentRequest.setId(comment.getId());
//            commentRequest.setPost(comment.getPost());
            commentRequest.setTitle(comment.getTitle());
            commentRequest.setEmail(comment.getEmail());
            commentRequest.setComments(comment.getComments());
            commentRequest.setCreateAt(comment.getCreateAt());
            commentRequest.setUpdatedAt(comment.getUpdatedAt());
            return commentRequest;
        }).collect(Collectors.toList());
    }
}
