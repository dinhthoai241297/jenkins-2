package com.digi.api.dto.category;

import com.digi.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class CategoryAdminDto extends ABasicAdminDto {

    private String categoryName;
    private Long parentId;
    private String description;
    private Integer ordering;
    private Long organizeId;
}
