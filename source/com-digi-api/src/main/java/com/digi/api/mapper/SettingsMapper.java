/*
 * File Created: Saturday, 30th January 2021 3:23:11 pm
 * Author: Bui Si Quan (18110041@student.hcmute.edu.vn)
 * -----
 */
package com.digi.api.mapper;

import java.util.List;

import com.digi.api.dto.settings.SettingsAdminDto;
import com.digi.api.dto.settings.SettingsDto;
import com.digi.api.form.settings.CreateSettingsForm;
import com.digi.api.form.settings.UpdateSettingsForm;
import com.digi.api.storage.model.Settings;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SettingsMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "key", target = "key")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "group", target = "group")
    @Mapping(source = "editable", target = "editable")
    SettingsDto fromEntityToDto(Settings settings);

    @IterableMapping(elementTargetType = SettingsDto.class)
    List<SettingsDto> fromEntityListToDtoList(List<Settings> list);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "key", target = "key")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "group", target = "group")
    @Mapping(source = "editable", target = "editable")
    SettingsAdminDto fromEntityToAdminDto(Settings settings);

    @IterableMapping(elementTargetType = SettingsAdminDto.class)
    List<SettingsAdminDto> fromEntityListToAdminDtoList(List<Settings> list);

    // create form to entity
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "settingKey", target = "key")
    @Mapping(source = "settingValue", target = "value")
    @Mapping(source = "settingGroup", target = "group")
    @Mapping(source = "editable", target = "editable")
    Settings fromCreateSettingsToSetting(CreateSettingsForm createSettingsForm);

    // update form to entity
    @Mapping(source = "name", target = "settings.name")
    @Mapping(source = "description", target = "settings.description")
    @Mapping(source = "settingKey", target = "settings.key")
    @Mapping(source = "settingValue", target = "settings.value")
    @Mapping(source = "settingGroup", target = "settings.group")
    Settings fromUpdateSettingsToSetting(UpdateSettingsForm updateSettingsForm,
            @MappingTarget Settings settings);

}
