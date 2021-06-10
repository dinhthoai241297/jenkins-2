package com.digi.api.dto.settings;

import com.digi.api.dto.ABasicAdminDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SettingsAdminDto extends ABasicAdminDto{
    @ApiModelProperty(name = "name")
    private String name;

    @ApiModelProperty(name = "key")
    private String key;

    @ApiModelProperty(name = "value")
    private String value;

    @ApiModelProperty(name = "description")
    private String description;

    @ApiModelProperty(name = "group")
    private String group;

    @ApiModelProperty(name = "editable")
    private boolean editable;
}
