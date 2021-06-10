package com.digi.api.mapper;

import com.digi.api.dto.news.NewsAdminDto;
import com.digi.api.dto.news.NewsDto;
import com.digi.api.form.news.CreateNewsForm;
import com.digi.api.form.news.UpdateNewsForm;
import com.digi.api.storage.model.News;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "newTitle")
    @Mapping(source = "content", target = "newContent")
    @Mapping(source = "description", target = "newDescription")
    @Mapping(source = "avatar", target = "newAvatar")
    @Mapping(source = "ordering", target = "newOrdering")
    NewsDto fromEntityToDto(News news);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "newTitle")
    @Mapping(source = "content", target = "newContent")
    @Mapping(source = "description", target = "newDescription")
    @Mapping(source = "avatar", target = "newAvatar")
    @Mapping(source = "ordering", target = "newOrdering")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "status", target = "status")
    NewsAdminDto fromEntityToAdminDto(News news);

    @IterableMapping(elementTargetType = NewsAdminDto.class)
    List<NewsAdminDto> fromEntityListToAdminDtoList(List<News> newsList);

    @Mapping(source = "newTitle", target = "title")
    @Mapping(source = "newContent", target = "content")
    @Mapping(source = "newDescription", target = "description")
    @Mapping(source = "newAvatar", target = "avatar")
    @Mapping(source = "newOrdering", target = "ordering")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "status", target = "status")
    News fromCreateFormToEntity(CreateNewsForm createNewsForm);

    @Mapping(source = "newTitle", target = "title")
    @Mapping(source = "newContent", target = "content")
    @Mapping(source = "newDescription", target = "description")
    @Mapping(source = "newAvatar", target = "avatar")
    @Mapping(source = "newOrdering", target = "ordering")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "status", target = "status")
    void fromUpdateFormToEntity(UpdateNewsForm updateNewsForm, @MappingTarget News news);

    @IterableMapping(elementTargetType = NewsDto.class)
    List<NewsDto> fromEntityListToDtoList(List<News> newsList);
}
