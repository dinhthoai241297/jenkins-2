package com.digi.api.form.news;

import com.digi.api.validation.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Api
public class CreateNewsForm {
    @NotEmpty(message = "title can not be empty")
    @ApiModelProperty(name = "title")
    private String newTitle;
    @NotEmpty(message = "description can not be empty")
    @ApiModelProperty(name = "description")
    private String newDescription;
    @NotEmpty(message = "content can not be empty")
    @ApiModelProperty(name = "content")
    private String newContent;
    @NotEmpty(message = "avatar can not be empty")
    @ApiModelProperty(name = "avatar")
    private String newAvatar;
    @NotNull(message = "ordering can not be null")
    @ApiModelProperty(name = "ordering")
    private Integer newOrdering;
    @NotNull(message = "Category's id can not be null")
    @ApiModelProperty(name = "categoryId")
    private Long categoryId;
    @NotNull(message = "status path can not be null")
    @ApiModelProperty(name = "status")
    @Status(message = "Invalid status.", allowNull = false)
    private Integer status;
}
