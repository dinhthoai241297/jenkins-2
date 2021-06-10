package com.digi.api.dto.question;

import lombok.Data;

@Data
public class QuestionClientDto {
    private String id;
    private String questionName;
    private String questionContent;
    private String questionAvatar;
    private String questionAttachment;
    private Integer questionScore;
    private Integer questionKind;
}
