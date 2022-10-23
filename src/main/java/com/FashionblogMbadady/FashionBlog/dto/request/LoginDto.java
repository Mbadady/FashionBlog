package com.FashionblogMbadady.FashionBlog.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Login Model Information")
@Data
public class LoginDto {

    @ApiModelProperty(value = "Login username or email")
    private String usernameOrEmail;

    @ApiModelProperty(value = "Login password")
    @NotEmpty()
    @Size(min = 8, message = "Password should be minimum of 8 characters")
    private String password;
}
