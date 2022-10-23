package com.FashionblogMbadady.FashionBlog.controller;


import com.FashionblogMbadady.FashionBlog.dto.request.CreateLikeRequest;
import com.FashionblogMbadady.FashionBlog.entity.Like;
import com.FashionblogMbadady.FashionBlog.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(value = "Like Rest API")
@RestController
@RequestMapping("/api/v1")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @ApiOperation(value = "Create a Like Resource")
    @PostMapping("/likes/{postId}")
    public String AddLike(@PathVariable Long postId,
                          @Valid @RequestBody CreateLikeRequest request){
       return likeService.AddLike(postId, request);
    }

    @ApiOperation(value = "Get all Like Resources")
    @GetMapping("/likes/{postId}")
    public List<Like> getAllLikesByPosts(@PathVariable Long postId){
        return likeService.getLikesByPostsId(postId);
    }
}
