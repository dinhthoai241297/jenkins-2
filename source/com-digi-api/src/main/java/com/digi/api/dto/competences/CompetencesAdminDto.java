package com.digi.api.dto.competences;

import com.digi.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class CompetencesAdminDto extends ABasicAdminDto {
    private String competencesName;
    private String competencesDescriptionBasic;
    private String competencesDescriptionMedium;
    private String competencesDescriptionAdvanced;
    private Long surveyProfileId;
    private Long workFieldId;
}
