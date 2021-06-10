package com.digi.api.controller;

import com.digi.api.dto.ApiMessageDto;
import com.digi.api.dto.ResponseListObj;
import com.digi.api.dto.surveyProfile.SurveyProfileAutoCompleteDto;
import com.digi.api.dto.surveyProfile.SurveyProfileDto;
import com.digi.api.exception.UnauthorizationException;
import com.digi.api.form.surveyProfile.CreateSurveyProfileForm;
import com.digi.api.form.surveyProfile.UpdateSurveyProfileForm;
import com.digi.api.mapper.SurveyProfileMapper;
import com.digi.api.storage.criteria.SurveyProfileCriteria;
import com.digi.api.storage.model.SurveyProfile;
import com.digi.api.storage.repository.SurveyProfileRepository;
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
@RequestMapping("/v1/survey_profile")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class SurveyProfileController extends ABasicController {

    @Autowired
    private SurveyProfileRepository surveyProfileRepository;

    @Autowired
    private SurveyProfileMapper surveyProfileMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<SurveyProfileDto>> getList(SurveyProfileCriteria surveyProfileCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ResponseListObj<SurveyProfileDto>> apiMessageDto = new ApiMessageDto<>();
        Page<SurveyProfile> accountPage = surveyProfileRepository.findAll(surveyProfileCriteria.getSpecification(),pageable);
        ResponseListObj<SurveyProfileDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(surveyProfileMapper.fromEntityListToSurveyProfileDtoList(accountPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(accountPage.getTotalPages());
        responseListObj.setTotalElements(accountPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List survey profile success");
        return apiMessageDto;
    }

    @GetMapping(value = "/list-auto-complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<SurveyProfileAutoCompleteDto>> getListAutoComplete(SurveyProfileCriteria surveyProfileCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ResponseListObj<SurveyProfileAutoCompleteDto>> apiMessageDto = new ApiMessageDto<>();
        Page<SurveyProfile> accountPage = surveyProfileRepository.findAll(surveyProfileCriteria.getSpecificationAutoComplete(),pageable);
        ResponseListObj<SurveyProfileAutoCompleteDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(surveyProfileMapper.fromEntityListToSurveyProfileAutoCompleteDtoList(accountPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(accountPage.getTotalPages());
        responseListObj.setTotalElements(accountPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List auto complete survey profile success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<SurveyProfileDto> get(@PathVariable("id") Long id) {

        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to get.");
        }
        ApiMessageDto<SurveyProfileDto> apiMessageDto = new ApiMessageDto<>();

        SurveyProfile surveyProfile = surveyProfileRepository.findById(id).orElse(null);
        // check null
        if (surveyProfile == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Can not get survey profile");
            return apiMessageDto;
        }
        apiMessageDto.setData(surveyProfileMapper.fromEntityToSurveyProfileDto(surveyProfile));
        apiMessageDto.setMessage("Get survey profile success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateSurveyProfileForm createSurveyProfileForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        SurveyProfile surveyProfile = surveyProfileMapper.fromSurveyProfileFormToEntity(createSurveyProfileForm);

        surveyProfileRepository.save(surveyProfile);
        apiMessageDto.setMessage("Create survey profile success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateSurveyProfileForm updateSurveyProfileForm,
                                        BindingResult bindingResult) {

        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to update");
        }

        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        SurveyProfile surveyProfile = surveyProfileRepository.findById(updateSurveyProfileForm.getId()).orElse(null);

        if (surveyProfile == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Survey profile does not exist to update");
            return apiMessageDto;
        }
        surveyProfileMapper.fromUpdateSurveyProfileFormToEntity(updateSurveyProfileForm, surveyProfile);
        surveyProfileRepository.save(surveyProfile);
        apiMessageDto.setMessage("Update survey profile success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        SurveyProfile surveyProfile = surveyProfileRepository.findById(id).orElse(null);
        if(surveyProfile == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Survey profile doesn't exist to delete");
            return apiMessageDto;
        }
        surveyProfileRepository.deleteById(id);
        apiMessageDto.setMessage("Delete survey profile success");
        return apiMessageDto;
    }
}
