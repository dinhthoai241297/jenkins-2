package com.digi.api.form.customer;

import com.digi.api.validation.Gender;
import com.digi.api.validation.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Api
@Data
public class UpdateCustomerForm {
    @NotNull(message = "id can not be null")
    @ApiModelProperty(name = "id")
    private Long id;
    @ApiModelProperty(name = "email")
    @Email
    private String email;
    private String fullName;
    private String avatarPath;

    @Status(message = "Status invalid")
    private Integer status;
    @NotEmpty(message = "phone can not be empty")
    @ApiModelProperty(name = "phone")
    private String phone;
    private String lang;
    private Date birthday;
    private Long provinceId;
    private Long districtId;
    private Long wardId;
    private String address;

    @Gender(message = "Sex invalid.")
    private Integer sex;
}
