package com.FashionblogMbadady.FashionBlog.dto.request;


import com.FashionblogMbadady.FashionBlog.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


@ApiModel(description = "Sign up model information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    @ApiModelProperty(value = "Signup name")
    @NotEmpty(message = "Name field should not be null or empty")
    private String name;

    @ApiModelProperty(value = "Signup username")
    @NotEmpty(message = "Username should not be null or empty")
    private String username;

    @ApiModelProperty(value = "Signup email")
    @NotEmpty(message = "Email field should not be null or empty")
    @Email
    private String email;

    @ApiModelProperty(value = "Signup password")
    @NotEmpty
    @Size(min = 8, message = "Password should have 8 minimum characters")
    private String password;

   private Set<Role> roles;

}
