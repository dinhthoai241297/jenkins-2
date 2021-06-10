package com.digi.api.form.competences;

import com.digi.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCompetencesForm {
    @NotNull(message = "id can not be null")
    @ApiModelProperty(name = "id", required = true)
    private Long id;
    @NotEmpty(message = "CompetencesName cant not be null")
    @ApiModelProperty(name = "competencesName", required = true)
    private String competencesName;
    @NotEmpty(message = "Competences Description Basic cant not be null")
    @ApiModelProperty(name = "competencesDescriptionBasic", required = true)
    private String competencesDescriptionBasic;
    @NotEmpty(message = "Competences Description Medium cant not be null")
    @ApiModelProperty(name = "competencesDescriptionMedium", required = true)
    private String competencesDescriptionMedium;
    @NotEmpty(message = "Competences Description Advanced cant not be null")
    @ApiModelProperty(name = "competencesDescriptionAdvanced", required = true)
    private String competencesDescriptionAdvanced;
    @NotNull(message = "surveyProfileId can not be null")
    @ApiModelProperty(name = "surveyProfileId", required = true)
    private Long surveyProfileId;
    @NotNull(message = "workFieldId can not be null")
    @ApiModelProperty(name = "workFieldId", required = true)
    private Long workFieldId;
    @Status(message = "Status invalid")
    @ApiModelProperty(name = "status", required = true)
    private Integer status;
}
