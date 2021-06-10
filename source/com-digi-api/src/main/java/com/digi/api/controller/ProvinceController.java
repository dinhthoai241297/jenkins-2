package com.digi.api.controller;

import com.digi.api.constant.DigiConstant;
import com.digi.api.dto.ApiMessageDto;
import com.digi.api.dto.ResponseListObj;
import com.digi.api.dto.province.ProvinceAdminDto;
import com.digi.api.dto.province.ProvinceDto;
import com.digi.api.exception.UnauthorizationException;
import com.digi.api.form.province.CreateProvinceForm;
import com.digi.api.form.province.UpdateProvinceForm;
import com.digi.api.mapper.ProvinceMapper;
import com.digi.api.storage.criteria.ProvinceCriteria;
import com.digi.api.storage.model.Province;
import com.digi.api.storage.repository.ProvinceRepository;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("/v1/province")
public class ProvinceController extends ABasicController {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private ProvinceMapper provinceMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<ProvinceAdminDto>> getList(ProvinceCriteria provinceCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ResponseListObj<ProvinceAdminDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Province> provinceList = provinceRepository.findAll(provinceCriteria.getSpecification(),pageable);

        ResponseListObj<ProvinceAdminDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(provinceMapper.fromEntityListToAdminDtoList(provinceList.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(provinceList.getTotalPages());
        responseListObj.setTotalElements(provinceList.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List province success");
        return apiMessageDto;
    }

    @GetMapping(value = "/list_combobox", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<ProvinceDto>> getListCombobox(ProvinceCriteria provinceCriteria){
        if(provinceCriteria.getParentId()==null){
            provinceCriteria.setKind(DigiConstant.PROVINCE_KIND_PROVINCE);
        }
        provinceCriteria.setStatus(DigiConstant.STATUS_ACTIVE);
        provinceCriteria.setName(null);
        provinceCriteria.setId(null);

        ApiMessageDto<ResponseListObj<ProvinceDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Province> provinceList = provinceRepository.findAll(provinceCriteria.getSpecification(), Pageable.unpaged());

        ResponseListObj<ProvinceDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(provinceMapper.fromEntityListToDtoList(provinceList.getContent()));


        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List province success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ProvinceAdminDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ProvinceAdminDto> apiMessageDto = new ApiMessageDto<>();
        Province province = provinceRepository.findById(id).orElse(null);
        ProvinceAdminDto provinceDto = provinceMapper.fromEntityToAdminDto(province);
        apiMessageDto.setData(provinceDto);
        apiMessageDto.setMessage("Get province success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateProvinceForm createProvinceForm, BindingResult bindingResult){
        if(!isSuperAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Province province = provinceMapper.fromCreateProvinceToProvince(createProvinceForm);
        if(province.getParentProvince().getId()==null){
            province.setParentProvince(null);
        }
        provinceRepository.save(province);
        apiMessageDto.setMessage("Create province success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateProvinceForm updateProvinceForm, BindingResult bindingResult){
        if(!isSuperAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Province province = provinceRepository.findById(updateProvinceForm.getProvinceId()).orElse(null);
        if(province == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Province's id doesn't exist");
        }
        provinceMapper.fromUpdateProvinceToProvince(updateProvinceForm, province);
        provinceRepository.save(province);
        apiMessageDto.setMessage("Update province success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isSuperAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Province province = provinceRepository.findById(id).orElse(null);
        if(province == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Province doesn't exist to delete");
            return apiMessageDto;
        }
        provinceRepository.deleteById(id);
        apiMessageDto.setMessage("Delete province success");
        return apiMessageDto;
    }
}
