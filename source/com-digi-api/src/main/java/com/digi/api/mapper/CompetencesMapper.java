package com.digi.api.mapper;

import com.digi.api.dto.competences.CompetencesAdminDto;
import com.digi.api.dto.competences.CompetencesAutoCompleteDto;
import com.digi.api.form.competences.CreateCompetencesForm;
import com.digi.api.form.competences.UpdateCompetencesForm;
import com.digi.api.storage.model.Competences;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompetencesMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "competencesName")
    @Mapping(source = "descriptionBasic", target = "competencesDescriptionBasic")
    @Mapping(source = "descriptionMedium", target = "competencesDescriptionMedium")
    @Mapping(source = "descriptionAdvanced", target = "competencesDescriptionAdvanced")
    @Mapping(source = "surveyProfile.id", target = "surveyProfileId")
    @Mapping(source = "workField.id", target = "workFieldId")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "status", target = "status")
    CompetencesAdminDto fromEntityToCompetencesDto(Competences competences);

    @IterableMapping(elementTargetType = CompetencesAdminDto.class)
    List<CompetencesAdminDto> fromEntityListToCompetencesDtoList(List<Competences> content);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "competencesName")
    CompetencesAutoCompleteDto fromEntityToCompetencesAutoCompleteDto(Competences competences);

    @IterableMapping(elementTargetType = CompetencesAutoCompleteDto.class)
    List<CompetencesAutoCompleteDto> fromEntityListToCompetencesAutoCompleteDtoList(List<Competences> content);

    @Mapping(source = "competencesName", target = "name")
    @Mapping(source = "competencesDescriptionBasic", target = "descriptionBasic")
    @Mapping(source = "competencesDescriptionMedium", target = "descriptionMedium")
    @Mapping(source = "competencesDescriptionAdvanced", target = "descriptionAdvanced")
    @Mapping(source = "surveyProfileId", target = "surveyProfile.id")
    @Mapping(source = "workFieldId", target = "workField.id")
    Competences fromCompetencesFormToEntity(CreateCompetencesForm createCompetencesForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "competencesName", target = "name")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "competencesDescriptionBasic", target = "descriptionBasic")
    @Mapping(source = "competencesDescriptionMedium", target = "descriptionMedium")
    @Mapping(source = "competencesDescriptionAdvanced", target = "descriptionAdvanced")
    @Mapping(source = "surveyProfileId", target = "surveyProfile.id")
    @Mapping(source = "workFieldId", target = "workField.id")
    void fromUpdateCompetencesFormToEntity(UpdateCompetencesForm updateCompetencesForm, @MappingTarget Competences competences);
}
