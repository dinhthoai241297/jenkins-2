package com.digi.api.mapper;

import com.digi.api.dto.field.WorkFieldAdminDto;
import com.digi.api.dto.field.WorkFieldAutoCompleteDto;
import com.digi.api.form.field.CreateWorkFieldForm;
import com.digi.api.form.field.UpdateWorkFieldForm;
import com.digi.api.form.news.UpdateNewsForm;
import com.digi.api.storage.model.News;
import com.digi.api.storage.model.WorkField;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WorkFieldMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "workTitle")
    @Mapping(source = "imagePath", target = "workImage")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "descriptionBasic", target = "workDescriptionBasic")
    @Mapping(source = "descriptionMedium", target = "workDescriptionMedium")
    @Mapping(source = "descriptionAdvanced", target = "workDescriptionAdvanced")
    WorkFieldAdminDto fromEntityToWorkFieldDto(WorkField workField);

    @IterableMapping(elementTargetType = WorkFieldAdminDto.class)
    List<WorkFieldAdminDto> fromEntityListToWorkFieldDtoList(List<WorkField> content);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "workTitle")
    @Mapping(source = "imagePath", target = "workImage")
    WorkFieldAutoCompleteDto fromEntityToWorkFieldAutoCompleteDto(WorkField workField);

    @IterableMapping(elementTargetType = WorkFieldAutoCompleteDto.class)
    List<WorkFieldAutoCompleteDto> fromEntityListToWorkFieldAutoCompleteDtoList(List<WorkField> content);

    @Mapping(source = "name", target = "title")
    @Mapping(source = "workImage", target = "imagePath")
    @Mapping(source = "workDescriptionBasic", target = "descriptionBasic")
    @Mapping(source = "workDescriptionMedium", target = "descriptionMedium")
    @Mapping(source = "workDescriptionAdvanced", target = "descriptionAdvanced")
    WorkField fromWorkFieldFormToEntity(CreateWorkFieldForm createWorkFieldForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "title")
    @Mapping(source = "workImage", target = "imagePath")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "workDescriptionBasic", target = "descriptionBasic")
    @Mapping(source = "workDescriptionMedium", target = "descriptionMedium")
    @Mapping(source = "workDescriptionAdvanced", target = "descriptionAdvanced")
    void fromUpdateWorkFieldFormToEntity(UpdateWorkFieldForm updateWorkFieldForm, @MappingTarget WorkField workField);
}
