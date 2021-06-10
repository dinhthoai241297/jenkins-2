package com.digi.api.form.surveyProfile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class CreateSurveyProfileForm {
    @NotEmpty(message = "surveyName cant not be null")
    @ApiModelProperty(name = "surveyName", required = true)
    private String surveyName;
    @NotEmpty(message = "surveyDescription cant not be null")
    @ApiModelProperty(name = "surveyDescription", required = true)
    private String surveyDescription;
    @NotEmpty(message = "surveyImage cant not be null")
    @ApiModelProperty(name = "surveyImage", required = true)
    private String surveyImage;
}
