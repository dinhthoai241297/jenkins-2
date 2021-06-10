package com.digi.api.mapper;

import com.digi.api.dto.surveyProfile.SurveyProfileAutoCompleteDto;
import com.digi.api.dto.surveyProfile.SurveyProfileDto;
import com.digi.api.form.surveyProfile.CreateSurveyProfileForm;
import com.digi.api.form.surveyProfile.UpdateSurveyProfileForm;
import com.digi.api.storage.model.SurveyProfile;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SurveyProfileMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "surveyName")
    @Mapping(source = "description", target = "surveyDescription")
    @Mapping(source = "imagePath", target = "surveyImage")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "status", target = "status")
    SurveyProfileDto fromEntityToSurveyProfileDto(SurveyProfile surveyProfile);

    @IterableMapping(elementTargetType = SurveyProfileDto.class)
    List<SurveyProfileDto> fromEntityListToSurveyProfileDtoList(List<SurveyProfile> content);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "surveyName")
    @Mapping(source = "imagePath", target = "surveyImage")
    SurveyProfileAutoCompleteDto fromEntityToSurveyProfileAutoCompleteDto(SurveyProfile surveyProfile);

    @IterableMapping(elementTargetType = SurveyProfileAutoCompleteDto.class)
    List<SurveyProfileAutoCompleteDto> fromEntityListToSurveyProfileAutoCompleteDtoList(List<SurveyProfile> content);

    @Mapping(source = "surveyName", target = "name")
    @Mapping(source = "surveyDescription", target = "description")
    @Mapping(source = "surveyImage", target = "imagePath")
    SurveyProfile fromSurveyProfileFormToEntity(CreateSurveyProfileForm createSurveyProfileForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "surveyName", target = "name")
    @Mapping(source = "surveyDescription", target = "description")
    @Mapping(source = "surveyImage", target = "imagePath")
    @Mapping(source = "status", target = "status")
    void fromUpdateSurveyProfileFormToEntity(UpdateSurveyProfileForm updateSurveyProfileForm, @MappingTarget SurveyProfile surveyProfile);
}
