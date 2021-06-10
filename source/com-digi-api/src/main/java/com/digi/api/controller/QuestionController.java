package com.digi.api.controller;

import com.digi.api.dto.ApiMessageDto;
import com.digi.api.dto.ResponseListObj;
import com.digi.api.dto.question.QuestionAdminDto;
import com.digi.api.dto.question.QuestionClientDto;
import com.digi.api.exception.UnauthorizationException;
import com.digi.api.form.question.CreateQuestionForm;
import com.digi.api.form.question.UpdateQuestionForm;
import com.digi.api.mapper.QuestionMapper;
import com.digi.api.storage.criteria.QuestionCriteria;
import com.digi.api.storage.model.Competences;
import com.digi.api.storage.model.Question;
import com.digi.api.storage.repository.CompetencesRepository;
import com.digi.api.storage.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/question")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class QuestionController extends ABasicController {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CompetencesRepository competencesRepository;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<QuestionAdminDto>> getList(QuestionCriteria questionCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ResponseListObj<QuestionAdminDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Question> accountPage = questionRepository.findAll(questionCriteria.getSpecification(),pageable);
        ResponseListObj<QuestionAdminDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(questionMapper.fromEntityListToQuestionDtoList(accountPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(accountPage.getTotalPages());
        responseListObj.setTotalElements(accountPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List Question success");
        return apiMessageDto;
    }

    @GetMapping(value = "/list-auto-complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<QuestionClientDto>> getListAutoComplete(QuestionCriteria questionCriteria, Pageable pageable){
        ApiMessageDto<ResponseListObj<QuestionClientDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Question> accountPage = questionRepository.findAll(questionCriteria.getSpecificationAutoComplete(),pageable);
        ResponseListObj<QuestionClientDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(questionMapper.fromEntityListToQuestionClientDtoList(accountPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(accountPage.getTotalPages());
        responseListObj.setTotalElements(accountPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List Question auto complete success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<QuestionAdminDto> get(@PathVariable("id") Long id) {

        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to get.");
        }
        ApiMessageDto<QuestionAdminDto> apiMessageDto = new ApiMessageDto<>();

        Question question = questionRepository.findById(id).orElse(null);
        // check null
        if (question == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Can not get Question");
            return apiMessageDto;
        }
        apiMessageDto.setData(questionMapper.fromEntityToQuestionDto(question));
        apiMessageDto.setMessage("Get Question success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateQuestionForm createQuestionForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Competences competences = competencesRepository.findById(createQuestionForm.getQuestionCompetencesId()).orElse(null);
        if (null == competences) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("questCompetencesId invalid");
            return apiMessageDto;
        }
        Question question = questionMapper.fromQuestionFormToEntity(createQuestionForm);

        questionRepository.save(question);
        apiMessageDto.setMessage("Create Question success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateQuestionForm updateQuestionForm,
                                        BindingResult bindingResult) {

        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to update");
        }

        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Question question = questionRepository.findById(updateQuestionForm.getId()).orElse(null);

        if (question == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Question does not exist to update");
            return apiMessageDto;
        }

        Competences competences = competencesRepository.findById(updateQuestionForm.getQuestionCompetencesId()).orElse(null);
        if (null == competences) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("questCompetencesId invalid");
            return apiMessageDto;
        }
        questionMapper.fromUpdateQuestionFormToEntity(updateQuestionForm, question);
        questionRepository.save(question);
        apiMessageDto.setMessage("Update Question success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Question question = questionRepository.findById(id).orElse(null);
        if(question == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Question doesn't exist to delete");
            return apiMessageDto;
        }
        questionRepository.deleteById(id);
        apiMessageDto.setMessage("Delete Question success");
        return apiMessageDto;
    }
}
