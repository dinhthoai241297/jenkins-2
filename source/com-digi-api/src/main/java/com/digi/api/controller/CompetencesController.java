package com.digi.api.controller;

import com.digi.api.dto.ApiMessageDto;
import com.digi.api.dto.ResponseListObj;
import com.digi.api.dto.competences.CompetencesAdminDto;
import com.digi.api.dto.competences.CompetencesAutoCompleteDto;
import com.digi.api.exception.UnauthorizationException;
import com.digi.api.form.competences.CreateCompetencesForm;
import com.digi.api.form.competences.UpdateCompetencesForm;
import com.digi.api.mapper.CompetencesMapper;
import com.digi.api.storage.criteria.CompetencesCriteria;
import com.digi.api.storage.model.Competences;
import com.digi.api.storage.model.SurveyProfile;
import com.digi.api.storage.model.WorkField;
import com.digi.api.storage.repository.CompetencesRepository;
import com.digi.api.storage.repository.SurveyProfileRepository;
import com.digi.api.storage.repository.WorkFieldRepository;
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
@RequestMapping("/v1/competences")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CompetencesController extends ABasicController{
    @Autowired
    private CompetencesRepository competencesRepository;

    @Autowired
    private CompetencesMapper competencesMapper;

    @Autowired
    private SurveyProfileRepository surveyProfileRepository;

    @Autowired
    private WorkFieldRepository workFieldRepository;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CompetencesAdminDto>> getList(CompetencesCriteria competencesCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ResponseListObj<CompetencesAdminDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Competences> accountPage = competencesRepository.findAll(competencesCriteria.getSpecification(),pageable);
        ResponseListObj<CompetencesAdminDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(competencesMapper.fromEntityListToCompetencesDtoList(accountPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(accountPage.getTotalPages());
        responseListObj.setTotalElements(accountPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List work field success");
        return apiMessageDto;
    }

    @GetMapping(value = "/list-auto-complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CompetencesAutoCompleteDto>> getListAutoComplete(CompetencesCriteria competencesCriteria, Pageable pageable){
        ApiMessageDto<ResponseListObj<CompetencesAutoCompleteDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Competences> accountPage = competencesRepository.findAll(competencesCriteria.getSpecificationAutoComplete(),pageable);
        ResponseListObj<CompetencesAutoCompleteDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(competencesMapper.fromEntityListToCompetencesAutoCompleteDtoList(accountPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(accountPage.getTotalPages());
        responseListObj.setTotalElements(accountPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List Competences auto complete success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CompetencesAdminDto> get(@PathVariable("id") Long id) {

        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to get.");
        }
        ApiMessageDto<CompetencesAdminDto> apiMessageDto = new ApiMessageDto<>();

        Competences competences = competencesRepository.findById(id).orElse(null);
        // check null
        if (competences == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Can not get Competences");
            return apiMessageDto;
        }
        apiMessageDto.setData(competencesMapper.fromEntityToCompetencesDto(competences));
        apiMessageDto.setMessage("Get Competences success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCompetencesForm createCompetencesForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        SurveyProfile surveyProfile = surveyProfileRepository.findById(createCompetencesForm.getSurveyProfileId()).orElse(null);
        if (surveyProfile == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("surveyProfileId invalid");
            return apiMessageDto;
        }
        WorkField workField = workFieldRepository.findById(createCompetencesForm.getWorkFieldId()).orElse(null);
        if (workField == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("workFieldId invalid");
            return apiMessageDto;
        }
        Competences competences = competencesMapper.fromCompetencesFormToEntity(createCompetencesForm);

        competencesRepository.save(competences);
        apiMessageDto.setMessage("Create category success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCompetencesForm updateCompetencesForm,
                                        BindingResult bindingResult) {

        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to update");
        }

        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Competences competences = competencesRepository.findById(updateCompetencesForm.getId()).orElse(null);

        if (competences == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Competences does not exist to update");
            return apiMessageDto;
        }
        SurveyProfile surveyProfile = surveyProfileRepository.findById(updateCompetencesForm.getSurveyProfileId()).orElse(null);
        if (surveyProfile == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("surveyProfileId invalid");
            return apiMessageDto;
        }
        WorkField workField = workFieldRepository.findById(updateCompetencesForm.getWorkFieldId()).orElse(null);
        if (workField == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("workFieldId invalid");
            return apiMessageDto;
        }
        competencesMapper.fromUpdateCompetencesFormToEntity(updateCompetencesForm, competences);
        competencesRepository.save(competences);
        apiMessageDto.setMessage("Update Competences success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Competences competences = competencesRepository.findById(id).orElse(null);
        if(competences == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Competences doesn't exist to delete");
            return apiMessageDto;
        }
        competencesRepository.deleteById(id);
        apiMessageDto.setMessage("Delete Competences success");
        return apiMessageDto;
    }
}
