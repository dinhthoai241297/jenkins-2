package com.digi.api.form.field;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateWorkFieldForm {
    @NotEmpty(message = "Name cant not be null")
    @ApiModelProperty(name = "name", required = true)
    private String name;
    @NotEmpty(message = "WorkImage cant not be null")
    @ApiModelProperty(name = "workImage", required = true)
    private String workImage;
    @NotEmpty(message = "Work Description Basic cant not be null")
    @ApiModelProperty(name = "workDescriptionBasic", required = true)
    private String workDescriptionBasic;
    @NotEmpty(message = "Work Description Medium cant not be null")
    @ApiModelProperty(name = "workDescriptionMedium", required = true)
    private String workDescriptionMedium;
    @NotEmpty(message = "Work Description Advanced cant not be null")
    @ApiModelProperty(name = "workDescriptionAdvanced", required = true)
    private String workDescriptionAdvanced;
}
