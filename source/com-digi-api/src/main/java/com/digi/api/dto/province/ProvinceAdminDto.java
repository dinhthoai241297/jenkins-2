package com.digi.api.dto.province;

import com.digi.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class ProvinceAdminDto extends ABasicAdminDto {
    private String provinceName;
    private Long parentId;
    private String kind;
}
