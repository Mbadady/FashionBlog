package com.FashionblogMbadady.FashionBlog.dto.request;

import com.FashionblogMbadady.FashionBlog.entity.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@ApiModel(description = "Like Model information ")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLikeRequest {

    @ApiModelProperty(value = "Blog Like email")
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private  String email;

    @ApiModelProperty(value = "Liked post")
    private Post post;
}
