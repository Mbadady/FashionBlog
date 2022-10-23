package com.FashionblogMbadady.FashionBlog.controller;


import com.FashionblogMbadady.FashionBlog.dto.request.CreatePostRequest;
import com.FashionblogMbadady.FashionBlog.dto.request.PostDtoCustom;
import com.FashionblogMbadady.FashionBlog.entity.Post;
import com.FashionblogMbadady.FashionBlog.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(value = "Post Rest API")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Create a post Resources")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CreatePostRequest createPost(@Valid @RequestBody CreatePostRequest request){
      return postService.createPost(request);
    }

    @ApiOperation(value = "Get a post Resource by Post ID")
    @GetMapping("/{id}")
    public CreatePostRequest getAPost(@PathVariable Long id){
        return postService.getAPostById(id);
    }

    @ApiOperation(value = "Get all post Resources")
    @GetMapping
    public PostDtoCustom getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                     @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                     @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
      return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "Get a post Resource by Post Category")
    @GetMapping("/category/{category}")
    public List<CreatePostRequest> findByCategory(@PathVariable String category){
        return postService.findByCategory(category);
    }

    @ApiOperation(value = "Update a Post resource")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CreatePostRequest updatePost(@Valid @RequestBody CreatePostRequest request, @PathVariable Long id){
        return postService.updatePost(request, id);
    }

    @ApiOperation(value = "Delete a post Resource by Post ID")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id){
      return postService.deletePost(id);
    }
}
