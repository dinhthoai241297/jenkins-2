package com.digi.api.form.province;

import com.digi.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateProvinceForm {
    @NotNull(message = "Province's id can not be null")
    private Long provinceId;
    @NotEmpty(message = "Province's name can not be empty")
    private String provinceName;
    @NotEmpty(message = "Province's kind can not be empty")
    private String kind;
    @NotNull(message = "status path can not be null")
    @ApiModelProperty(name = "status")
    @Status(message = "Status invalid.", allowNull = false)
    private Integer status;

}
