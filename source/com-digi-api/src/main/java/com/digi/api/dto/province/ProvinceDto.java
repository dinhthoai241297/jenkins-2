package com.digi.api.dto.province;

import lombok.Data;

@Data
public class ProvinceDto {
    private Long provinceId;
    private String provinceName;
    private Long parentId;
    private String kind;
}
