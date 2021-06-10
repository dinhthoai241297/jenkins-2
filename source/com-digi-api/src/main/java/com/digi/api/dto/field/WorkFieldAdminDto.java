package com.digi.api.dto.field;

import com.digi.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class WorkFieldAdminDto extends ABasicAdminDto {
    private String workTitle;
    private String workImage;
    private String workDescriptionBasic;
    private String workDescriptionMedium;
    private String workDescriptionAdvanced;
}
