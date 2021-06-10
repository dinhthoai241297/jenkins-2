package com.digi.api.dto.field;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WorkFieldAutoCompleteDto {
    private Long id;
    private String workTitle;
    private String workImage;
}
