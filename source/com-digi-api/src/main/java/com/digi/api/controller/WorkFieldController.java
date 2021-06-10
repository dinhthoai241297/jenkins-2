package com.digi.api.controller;

import com.digi.api.dto.ApiMessageDto;
import com.digi.api.dto.ResponseListObj;
import com.digi.api.dto.field.WorkFieldAdminDto;
import com.digi.api.dto.field.WorkFieldAutoCompleteDto;
import com.digi.api.dto.settings.SettingsAdminDto;
import com.digi.api.exception.UnauthorizationException;
import com.digi.api.form.field.CreateWorkFieldForm;
import com.digi.api.form.field.UpdateWorkFieldForm;
import com.digi.api.mapper.WorkFieldMapper;
import com.digi.api.storage.criteria.WorkFieldCriteria;
import com.digi.api.storage.model.Settings;
import com.digi.api.storage.model.WorkField;
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
@RequestMapping("/v1/work_field")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class WorkFieldController extends ABasicController{

    @Autowired
    private WorkFieldRepository workFieldRepository;

    @Autowired
    private WorkFieldMapper workFieldMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<WorkFieldAdminDto>> getList(WorkFieldCriteria workFieldCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ResponseListObj<WorkFieldAdminDto>> apiMessageDto = new ApiMessageDto<>();
        Page<WorkField> accountPage = workFieldRepository.findAll(workFieldCriteria.getSpecification(),pageable);
        ResponseListObj<WorkFieldAdminDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(workFieldMapper.fromEntityListToWorkFieldDtoList(accountPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(accountPage.getTotalPages());
        responseListObj.setTotalElements(accountPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List work field success");
        return apiMessageDto;
    }

    @GetMapping(value = "/list-auto-complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<WorkFieldAutoCompleteDto>> getListAutoComplete(WorkFieldCriteria workFieldCriteria, Pageable pageable){
        ApiMessageDto<ResponseListObj<WorkFieldAutoCompleteDto>> apiMessageDto = new ApiMessageDto<>();
        Page<WorkField> accountPage = workFieldRepository.findAll(workFieldCriteria.getSpecificationAutoComplete(),pageable);
        ResponseListObj<WorkFieldAutoCompleteDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(workFieldMapper.fromEntityListToWorkFieldAutoCompleteDtoList(accountPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(accountPage.getTotalPages());
        responseListObj.setTotalElements(accountPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List work field auto complete success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<WorkFieldAdminDto> get(@PathVariable("id") Long id) {

        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to get.");
        }
        ApiMessageDto<WorkFieldAdminDto> apiMessageDto = new ApiMessageDto<>();

        WorkField workField = workFieldRepository.findById(id).orElse(null);
        // check null
        if (workField == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Can not get WorkField");
            return apiMessageDto;
        }
        apiMessageDto.setData(workFieldMapper.fromEntityToWorkFieldDto(workField));
        apiMessageDto.setMessage("Get WorkField success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateWorkFieldForm createCategoryForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        WorkField workField = workFieldMapper.fromWorkFieldFormToEntity(createCategoryForm);

        workFieldRepository.save(workField);
        apiMessageDto.setMessage("Create category success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateWorkFieldForm updateWorkFieldForm,
                                        BindingResult bindingResult) {

        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to update");
        }

        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        WorkField workField = workFieldRepository.findById(updateWorkFieldForm.getId()).orElse(null);

        if (workField == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("WorkField does not exist to update");
            return apiMessageDto;
        }
        workFieldMapper.fromUpdateWorkFieldFormToEntity(updateWorkFieldForm, workField);
        workFieldRepository.save(workField);
        apiMessageDto.setMessage("Update WorkField success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        WorkField workField = workFieldRepository.findById(id).orElse(null);
        if(workField == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("WorkField doesn't exist to delete");
            return apiMessageDto;
        }
        workFieldRepository.deleteById(id);
        apiMessageDto.setMessage("Delete WorkField success");
        return apiMessageDto;
    }
}
