package com.digi.api.mapper;

import com.digi.api.dto.category.CategoryAdminDto;
import com.digi.api.dto.category.CategoryDto;
import com.digi.api.form.category.CreateCategoryForm;
import com.digi.api.form.category.UpdateCategoryForm;
import com.digi.api.storage.model.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "name", target = "categoryName")
    @Mapping(source = "parentCategory.id", target = "parentId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "ordering", target = "ordering")
    CategoryDto fromEntityToDto(Category category);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "categoryName")
    @Mapping(source = "parentCategory.id", target = "parentId")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "ordering", target = "ordering")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "status", target = "status")
    CategoryAdminDto fromEntityToAdminDto(Category category);

    @Mapping(source = "categoryName", target = "name")
    @Mapping(source = "parentId", target = "parentCategory.id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "ordering", target = "ordering")
    Category fromCreateCategoryToCategory(CreateCategoryForm createCategoryForm);

    @Mapping(source = "categoryName", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "ordering", target = "ordering")
    @Mapping(source = "status", target = "status")
    void fromUpdateCategoryToCategory(UpdateCategoryForm updateCategoryForm, @MappingTarget Category category);

    @IterableMapping(elementTargetType = CategoryAdminDto.class)
    List<CategoryAdminDto> fromEntityListToAdminDtoList(List<Category> content);

    @IterableMapping(elementTargetType = CategoryDto.class)
    List<CategoryDto> fromEntityListToDtoList(List<Category> content);
}
