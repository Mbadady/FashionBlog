package com.FashionblogMbadady.FashionBlog.service;

import com.FashionblogMbadady.FashionBlog.dto.request.CreateCommentRequest;
import com.FashionblogMbadady.FashionBlog.entity.Comment;
import com.FashionblogMbadady.FashionBlog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    CreateCommentRequest createComment(Long postId, CreateCommentRequest request);

    CreateCommentRequest updateComment(Long postId, Long commentId, CreateCommentRequest request);

    String deleteComment(Long postId, Long commentId);

    List<CreateCommentRequest> getCommentsByPosts(Long postId);

    List<CreateCommentRequest> findByEmail(String email);


}
