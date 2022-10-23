package com.FashionblogMbadady.FashionBlog.dto.request;

import com.FashionblogMbadady.FashionBlog.entity.Comment;
import com.FashionblogMbadady.FashionBlog.entity.Like;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@ApiModel(description = "Post Model Information")
@Data
public class CreatePostRequest {

    @ApiModelProperty(value = "Blog Post ID")
    private Long id;

    @ApiModelProperty(value = "Blog Post Category")
    @NotEmpty
    @Size(min = 2, message = "Post category should be minimum of 2 characters")
    private String category; // the category such as classic, traditional etc

    @ApiModelProperty(value = "Blog post description")
    @NotEmpty
    @Size(min = 10, message = "Post description should not be less than 10 characters")
    private  String description; // the body of the post under the category

    @ApiModelProperty(value = "Blog post email")
    @NotEmpty(message = "Email field should not be null or empty")
    @Email
    private String email;

//    private String content;

    @ApiModelProperty(value = "Blog post comments")
    private Set<Comment> comments;

}
