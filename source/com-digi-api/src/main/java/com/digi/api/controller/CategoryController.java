package com.digi.api.controller;

import com.digi.api.constant.DigiConstant;
import com.digi.api.dto.ApiMessageDto;
import com.digi.api.dto.ResponseListObj;
import com.digi.api.dto.category.CategoryAdminDto;
import com.digi.api.dto.category.CategoryDto;
import com.digi.api.exception.UnauthorizationException;
import com.digi.api.form.category.CreateCategoryForm;
import com.digi.api.form.category.UpdateCategoryForm;
import com.digi.api.mapper.CategoryMapper;
import com.digi.api.storage.criteria.CategoryCriteria;
import com.digi.api.storage.model.Category;
import com.digi.api.storage.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequestMapping("/v1/category")
public class CategoryController extends ABasicController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CategoryAdminDto>> getList(CategoryCriteria categoryCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<ResponseListObj<CategoryAdminDto>> apiMessageDto = new ApiMessageDto<>();
        Page<Category> categoryList = categoryRepository.findAll(categoryCriteria.getSpecification(),pageable);

        ResponseListObj<CategoryAdminDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(categoryMapper.fromEntityListToAdminDtoList(categoryList.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(categoryList.getTotalPages());
        responseListObj.setTotalElements(categoryList.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List category success");
        return apiMessageDto;
    }

    @GetMapping(value = "/list_combobox/{kind}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CategoryDto>> getListCombobox(@PathVariable @NotNull Integer kind){
        ApiMessageDto<ResponseListObj<CategoryDto>> apiMessageDto = new ApiMessageDto<>();

        CategoryCriteria categoryCriteria = new CategoryCriteria();
        categoryCriteria.setStatus(DigiConstant.STATUS_ACTIVE);
        Page<Category> categoryList = categoryRepository.findAll(categoryCriteria.getSpecification(), Pageable.unpaged());

        ResponseListObj<CategoryDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(categoryMapper.fromEntityListToDtoList(categoryList.getContent()));

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List category success");
        return apiMessageDto;
    }


    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CategoryAdminDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<CategoryAdminDto> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Can not get category");
            return apiMessageDto;
        }
        CategoryAdminDto categoryDto = categoryMapper.fromEntityToAdminDto(category);
        apiMessageDto.setData(categoryDto);
        apiMessageDto.setMessage("Get category success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCategoryForm createCategoryForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryMapper.fromCreateCategoryToCategory(createCategoryForm);
        if(category.getParentCategory().getId()==null){
            category.setParentCategory(null);
        }
        categoryRepository.save(category);
        apiMessageDto.setMessage("Create category success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCategoryForm updateCategoryForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(updateCategoryForm.getCategoryId()).orElse(null);
        if(category == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Category's id doesn't exist");
            return apiMessageDto;
        }
        categoryMapper.fromUpdateCategoryToCategory(updateCategoryForm, category);
        categoryRepository.save(category);
        apiMessageDto.setMessage("Update category success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Category doesn't exist to delete");
            return apiMessageDto;
        }
        categoryRepository.deleteById(id);
        apiMessageDto.setMessage("Delete category success");
        return apiMessageDto;
    }
}
