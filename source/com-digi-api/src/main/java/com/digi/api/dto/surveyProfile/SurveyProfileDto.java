package com.digi.api.dto.surveyProfile;

import com.digi.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class SurveyProfileDto extends ABasicAdminDto {
    private Long id;
    private String surveyName;
    private String surveyDescription;
    private String surveyImage;
}
