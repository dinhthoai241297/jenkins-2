package com.digi.api.controller;

import com.digi.api.dto.ApiMessageDto;
import com.digi.api.dto.ResponseListObj;
import com.digi.api.dto.news.NewsAdminDto;
import com.digi.api.exception.UnauthorizationException;
import com.digi.api.form.news.CreateNewsForm;
import com.digi.api.form.news.UpdateNewsForm;
import com.digi.api.mapper.NewsMapper;
import com.digi.api.storage.criteria.NewsCriteria;
import com.digi.api.storage.model.News;
import com.digi.api.storage.repository.NewsRepository;
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
@RequestMapping("/v1/news")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class NewsController extends ABasicController{
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsMapper newsMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<NewsAdminDto>> list(NewsCriteria newsCriteria, Pageable pageable){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to list");
        }
        ApiMessageDto<ResponseListObj<NewsAdminDto>> apiMessageDto = new ApiMessageDto<>();
        Page<News> newsPage = newsRepository.findAll(newsCriteria.getSpecification(), pageable);
        ResponseListObj<NewsAdminDto> newsList = new ResponseListObj<>();
        newsList.setData(newsMapper.fromEntityListToAdminDtoList(newsPage.getContent()));
        newsList.setPage(pageable.getPageNumber());
        newsList.setTotalPage(newsPage.getTotalPages());
        newsList.setTotalElements(newsPage.getTotalElements());

        apiMessageDto.setData(newsList);
        apiMessageDto.setMessage("List news success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<NewsAdminDto> get(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to get");
        }
        ApiMessageDto<NewsAdminDto> apiMessageDto = new ApiMessageDto<>();
        News news = newsRepository.findById(id).orElse(null);
        if (news == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Can not get news");
            return apiMessageDto;
        }
        NewsAdminDto newsDto = newsMapper.fromEntityToAdminDto(news);
        apiMessageDto.setData(newsDto);
        apiMessageDto.setMessage("Get new success");
        return apiMessageDto;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateNewsForm createNewsForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        News news = newsMapper.fromCreateFormToEntity(createNewsForm);
        newsRepository.save(news);
        apiMessageDto.setMessage("Create new success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateNewsForm updateNewsForm, BindingResult bindingResult){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        News news = newsRepository.findById(updateNewsForm.getId()).orElse(null);
        if(news == null){
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("News does not exist to update");
            return apiMessageDto;
        }
        newsMapper.fromUpdateFormToEntity(updateNewsForm, news);
        newsRepository.save(news);
        apiMessageDto.setMessage("Update news success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id){
        if(!isAdmin()){
            throw new UnauthorizationException("Not allowed to delete");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        News news = newsRepository.findById(id).orElse(null);
        if(news == null){
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("News does not exist to delete");
            return apiMessageDto;
        }
        newsRepository.deleteById(id);
        apiMessageDto.setMessage("Delete news success");
        return apiMessageDto;
    }
}
