package com.digi.api.dto.news;

import com.digi.api.dto.ABasicAdminDto;

import lombok.Data;

@Data
public class NewsAdminDto extends ABasicAdminDto {
    private String newTitle;
    private String newDescription;
    private String newContent;
    private String newAvatar;
    private Integer newKind;
    private Integer newOrdering;
    private Long organizeId;
    private Long categoryId;
}
