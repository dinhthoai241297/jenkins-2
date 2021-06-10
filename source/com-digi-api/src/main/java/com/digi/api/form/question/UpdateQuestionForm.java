package com.digi.api.form.question;

import com.digi.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateQuestionForm {
    @NotEmpty(message = "questionName cant not be null")
    @ApiModelProperty(name = "questionName", required = true)
    private String questionName;
    @NotEmpty(message = "questionContent cant not be null")
    @ApiModelProperty(name = "questionContent", required = true)
    private String questionContent;
    @NotEmpty(message = "questionAvatar cant not be null")
    @ApiModelProperty(name = "questionAvatar", required = true)
    private String questionAvatar;

    @ApiModelProperty(name = "questionAttachment", required = true)
    private String questionAttachment;
    @NotNull(message = "questionScore cant not be null")
    @ApiModelProperty(name = "questionScore", required = true)
    private Integer questionScore;
    @NotNull(message = "questionKind cant not be null")
    @ApiModelProperty(name = "questionKind", required = true)
    private Integer questionKind;
    @NotNull(message = "questionCompetencesId cant not be null")
    @ApiModelProperty(name = "questionCompetencesId", required = true)
    private Long questionCompetencesId;
    @NotNull(message = "id can not be null")
    @ApiModelProperty(name = "id", required = true)
    private Long id;
    @Status(message = "Status invalid")
    @ApiModelProperty(name = "status", required = true)
    private Integer status;
}
