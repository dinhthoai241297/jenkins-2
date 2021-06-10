package com.digi.api.dto.question;

import com.digi.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class QuestionAdminDto extends ABasicAdminDto {
    private String questionName;
    private String questionContent;
    private String questionAvatar;
    private String questionAttachment;
    private Integer questionScore;
    private Integer questionKind;
    private Long questionCompetencesId;
}
