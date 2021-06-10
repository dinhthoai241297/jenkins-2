package com.digi.api.form.surveyProfile;

import com.digi.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateSurveyProfileForm {
    @NotNull(message = "id can not be null")
    @ApiModelProperty(name = "id", required = true)
    private Long id;
    @NotEmpty(message = "surveyName cant not be null")
    @ApiModelProperty(name = "surveyName", required = true)
    private String surveyName;
    @NotNull(message = "surveyDescription can not be null")
    @ApiModelProperty(name = "surveyDescription", required = true)
    private String surveyDescription;
    @NotNull(message = "surveyImagePath can not be null")
    @ApiModelProperty(name = "surveyImagePath", required = true)
    private String surveyImage;
    @NotNull(message = "status can not be null")
    @ApiModelProperty(name = "status", required = true)
    @Status(message = "Status invalid.", allowNull = false)
    private Integer status;
}
