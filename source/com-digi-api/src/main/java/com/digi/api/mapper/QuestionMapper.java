package com.digi.api.mapper;

import com.digi.api.dto.question.QuestionAdminDto;
import com.digi.api.dto.question.QuestionClientDto;
import com.digi.api.storage.model.Question;
import com.digi.api.form.question.CreateQuestionForm;
import com.digi.api.form.question.UpdateQuestionForm;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {
    @Mapping(source = "name", target = "questionName")
    @Mapping(source = "question", target = "questionContent")
    @Mapping(source = "avatarPath", target = "questionAvatar")
    @Mapping(source = "attachmentPath", target = "questionAttachment")
    @Mapping(source = "score", target = "questionScore")
    @Mapping(source = "kind", target = "questionKind")
    @Mapping(source = "competences.id", target = "questionCompetencesId")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    QuestionAdminDto fromEntityToQuestionDto(Question question);

    @IterableMapping(elementTargetType = QuestionAdminDto.class)
    List<QuestionAdminDto> fromEntityListToQuestionDtoList(List<Question> content);

    @Mapping(source = "name", target = "questionName")
    @Mapping(source = "question", target = "questionContent")
    @Mapping(source = "avatarPath", target = "questionAvatar")
    @Mapping(source = "attachmentPath", target = "questionAttachment")
    @Mapping(source = "score", target = "questionScore")
    @Mapping(source = "kind", target = "questionKind")
    @Mapping(source = "id", target = "id")
    QuestionClientDto fromEntityToQuestionClientDto(Question question);

    @IterableMapping(elementTargetType = QuestionClientDto.class)
    List<QuestionClientDto> fromEntityListToQuestionClientDtoList(List<Question> content);

    @Mapping(source = "questionName", target = "name")
    @Mapping(source = "questionContent", target = "question")
    @Mapping(source = "questionAvatar", target = "avatarPath")
    @Mapping(source = "questionAttachment", target = "attachmentPath")
    @Mapping(source = "questionScore", target = "score")
    @Mapping(source = "questionKind", target = "kind")
    @Mapping(source = "questionCompetencesId", target = "competences.id")
    Question fromQuestionFormToEntity(CreateQuestionForm createQuestionForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "questionName", target = "name")
    @Mapping(source = "questionContent", target = "question")
    @Mapping(source = "questionAvatar", target = "avatarPath")
    @Mapping(source = "questionAttachment", target = "attachmentPath")
    @Mapping(source = "questionScore", target = "score")
    @Mapping(source = "questionKind", target = "kind")
    @Mapping(source = "questionCompetencesId", target = "competences.id")
    void fromUpdateQuestionFormToEntity(UpdateQuestionForm updateQuestionForm, @MappingTarget Question question);
}
