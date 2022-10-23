package com.FashionblogMbadady.FashionBlog.service;

import com.FashionblogMbadady.FashionBlog.dto.request.CreateLikeRequest;
import com.FashionblogMbadady.FashionBlog.entity.Like;

import java.util.List;


public interface LikeService {

    String AddLike(Long postId, CreateLikeRequest request);

    List<Like> getLikesByPostsId(Long postId);
}
