package com.digi.api.form.customer;

import com.digi.api.validation.Gender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Api
public class CreateCustomerForm {
    @NotEmpty(message = "user name can not be empty")
    @ApiModelProperty(name = "username")
    private String username;
    @ApiModelProperty(name = "email")
    @Email
    private String email;
    @NotEmpty(message = "password cant not be null")
    @ApiModelProperty(name = "password", required = true)
    private String password;
    private String fullName;
    private String avatarPath;
    @NotEmpty(message = "phone can not be empty")
    @ApiModelProperty(name = "phone")
    private String phone;
    private String lang;
    private Date birthday;
    private Long provinceId;
    private Long districtId;
    private Long wardId;
    private String address;
    @Gender
    private Integer sex;
}
