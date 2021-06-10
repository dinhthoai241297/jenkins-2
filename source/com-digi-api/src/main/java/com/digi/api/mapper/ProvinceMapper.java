package com.digi.api.mapper;

import com.digi.api.dto.province.ProvinceAdminDto;
import com.digi.api.dto.province.ProvinceDto;
import com.digi.api.form.province.CreateProvinceForm;
import com.digi.api.form.province.UpdateProvinceForm;
import com.digi.api.storage.model.Province;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProvinceMapper {
    @Mapping(source = "id", target = "provinceId")
    @Mapping(source = "name", target = "provinceName")
    @Mapping(source = "parentProvince.id", target = "parentId")
    @Mapping(source = "kind", target = "kind")
    ProvinceDto fromEntityToDto(Province province);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "provinceName")
    @Mapping(source = "parentProvince.id", target = "parentId")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "status", target = "status")
    ProvinceAdminDto fromEntityToAdminDto(Province province);

    @Mapping(source = "provinceName", target = "name")
    @Mapping(source = "parentId", target = "parentProvince.id")
    @Mapping(source = "kind", target = "kind")
    Province fromCreateProvinceToProvince(CreateProvinceForm createProvinceForm);

    @Mapping(source = "provinceName", target = "name")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "status", target = "status")
    void fromUpdateProvinceToProvince(UpdateProvinceForm updateProvinceForm, @MappingTarget Province province);

    @IterableMapping(elementTargetType = ProvinceAdminDto.class)
    List<ProvinceAdminDto> fromEntityListToAdminDtoList(List<Province> content);

    @IterableMapping(elementTargetType = ProvinceDto.class)
    List<ProvinceDto> fromEntityListToDtoList(List<Province> content);
}
