package com.FashionblogMbadady.FashionBlog.dto.request;

import com.FashionblogMbadady.FashionBlog.entity.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


@ApiModel(description = "Comment model information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @ApiModelProperty(value = "Blog Comment ID")
    private Long id;

    @ApiModelProperty(value = "Blog Comment Email")
    @NotEmpty(message = "Email field should not be null or empty")
    @Email
    private  String email;

    @ApiModelProperty(value = "Blog Comment title")
    @NotEmpty
    @Size(min = 2, message = "Comment title should be at least 2 characters")
    private String title;

    // title should not be null or empty
    // title should have at least 2 characters
    @ApiModelProperty(value = "Blog Comments Comment")
    @NotEmpty
    @Size(min = 10, message = "Comment should be at least 10 characters")
    private String comments;

    private Timestamp createAt;

    private Timestamp updatedAt;

//    private Post post;


}
