package com.digi.api.form.category;

import com.digi.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCategoryForm {
    @NotNull(message = "Category's id can not be null")
    private Long categoryId;
    @NotEmpty(message = "Category's name can not be empty")
    private String categoryName;
    @NotEmpty(message = "Category's description can not be empty")
    private String description;
    @NotNull(message = "Category's ordering can not be null")
    private Integer ordering;
    private Integer kind;
    @Status
    @NotNull(message = "status path can not be null")
    @ApiModelProperty(name = "status")
    private Integer status;
}
