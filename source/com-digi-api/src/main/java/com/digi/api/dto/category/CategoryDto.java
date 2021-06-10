package com.digi.api.dto.category;

import lombok.Data;

@Data
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private Long parentId;
    private String description;
    private Integer ordering;
    private Long organizeId;
}
