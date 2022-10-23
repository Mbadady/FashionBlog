package com.FashionblogMbadady.FashionBlog.service;

import com.FashionblogMbadady.FashionBlog.dto.request.CreatePostRequest;
import com.FashionblogMbadady.FashionBlog.dto.request.PostDtoCustom;
import com.FashionblogMbadady.FashionBlog.entity.Post;

import java.util.List;

public interface PostService {

   CreatePostRequest createPost(CreatePostRequest request);

    CreatePostRequest getAPostById(Long id);

    PostDtoCustom getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);


    List<CreatePostRequest> findByCategory(String category);


    CreatePostRequest updatePost(CreatePostRequest request, Long id);


    String deletePost(Long id);
}
