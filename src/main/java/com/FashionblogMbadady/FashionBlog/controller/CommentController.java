package com.FashionblogMbadady.FashionBlog.controller;


import com.FashionblogMbadady.FashionBlog.dto.request.CreateCommentRequest;
import com.FashionblogMbadady.FashionBlog.entity.Comment;
import com.FashionblogMbadady.FashionBlog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "Comment Rest API")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create a comment resource")
    @PostMapping("/{postId}/comments")
    public CreateCommentRequest createAcomment(@PathVariable Long postId,
                                               @Valid @RequestBody CreateCommentRequest request){
      return commentService.createComment(postId, request);
    }

    @ApiOperation(value = "Update a comment resource")
    @PutMapping("/{postId}/comments/{commentId}")
    public CreateCommentRequest updateAComment(@PathVariable  Long postId,
                                  @PathVariable Long commentId,
                                               @Valid @RequestBody CreateCommentRequest request){
        return commentService.updateComment(postId,commentId,request);
    }

    @ApiOperation(value = "Delete a comment resource by ID")
    @DeleteMapping("/{postId}/comments/{commentId}")
    public String deleteComment(@PathVariable Long postId,
                                @PathVariable Long commentId){
      return commentService.deleteComment(postId, commentId);
    }

    @ApiOperation(value = "Get a comment resource by Post ID")
    @GetMapping("/{postId}/comments")
    public List<CreateCommentRequest> getCommentsByPostsId(@PathVariable Long postId){
        return commentService.getCommentsByPosts(postId);
    }


    @ApiOperation(value = "Get a comment resource by email")
    @GetMapping("/comments/email/{email}")
    public List<CreateCommentRequest> getCommentsByEmail(@PathVariable String email){
        return commentService.findByEmail(email);
    }
}
