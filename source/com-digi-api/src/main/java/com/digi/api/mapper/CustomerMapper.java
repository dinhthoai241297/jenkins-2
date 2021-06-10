package com.digi.api.mapper;

import com.digi.api.dto.customer.CustomerAdminDto;
import com.digi.api.dto.customer.CustomerAutoCompleteDto;
import com.digi.api.dto.customer.CustomerClientDto;
import com.digi.api.form.customer.CreateCustomerForm;
import com.digi.api.form.customer.UpdateByCustomerForm;
import com.digi.api.form.customer.UpdateCustomerForm;
import com.digi.api.storage.model.Account;
import com.digi.api.storage.model.Customer;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "province.id", target = "provinceDto.provinceId")
    @Mapping(source = "province.name", target = "provinceDto.provinceName")
    @Mapping(source = "province.parentProvince.id", target = "provinceDto.parentId")
    @Mapping(source = "province.kind", target = "provinceDto.kind")
    @Mapping(source = "district.id", target = "districtDto.provinceId")
    @Mapping(source = "district.name", target = "districtDto.provinceName")
    @Mapping(source = "district.parentProvince.id", target = "districtDto.parentId")
    @Mapping(source = "district.kind", target = "districtDto.kind")
    @Mapping(source = "ward.id", target = "wardDto.provinceId")
    @Mapping(source = "ward.name", target = "wardDto.provinceName")
    @Mapping(source = "ward.parentProvince.id", target = "wardDto.parentId")
    @Mapping(source = "ward.kind", target = "wardDto.kind")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.kind", target = "kind")
    @Mapping(source = "account.fullName", target = "fullName")
    @Mapping(source = "account.avatarPath", target = "avatarPath")
    @Mapping(source = "account.phone", target = "phone")
    @Mapping(source = "account.lang", target = "lang")
    CustomerAdminDto fromAdminEntityToDto(Customer customer);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.fullName", target = "fullName")
    @Mapping(source = "account.avatarPath", target = "avatarPath")
    @Mapping(source = "account.phone", target = "phone")
    CustomerAutoCompleteDto fromAutoCompleteEntityToDto(Customer customer);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "province.id", target = "provinceDto.provinceId")
    @Mapping(source = "province.name", target = "provinceDto.provinceName")
    @Mapping(source = "province.parentProvince.id", target = "provinceDto.parentId")
    @Mapping(source = "province.kind", target = "provinceDto.kind")
    @Mapping(source = "district.id", target = "districtDto.provinceId")
    @Mapping(source = "district.name", target = "districtDto.provinceName")
    @Mapping(source = "district.parentProvince.id", target = "districtDto.parentId")
    @Mapping(source = "district.kind", target = "districtDto.kind")
    @Mapping(source = "ward.id", target = "wardDto.provinceId")
    @Mapping(source = "ward.name", target = "wardDto.provinceName")
    @Mapping(source = "ward.parentProvince.id", target = "wardDto.parentId")
    @Mapping(source = "ward.kind", target = "wardDto.kind")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.email", target = "email")
    @Mapping(source = "account.kind", target = "kind")
    @Mapping(source = "account.fullName", target = "fullName")
    @Mapping(source = "account.avatarPath", target = "avatarPath")
    @Mapping(source = "account.phone", target = "phone")
    @Mapping(source = "account.lang", target = "lang")
    CustomerClientDto fromClientEntityToDto(Customer customer);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "avatarPath", target = "avatarPath")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    Account fromCreateFormToAccount(CreateCustomerForm createCustomerForm);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "avatarPath", target = "avatarPath")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    @BeanMapping(ignoreByDefault = true)
    void fromUpdateFormToAccount(UpdateCustomerForm updateCustomerForm, @MappingTarget Account account);

    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "avatarPath", target = "avatarPath")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    @BeanMapping(ignoreByDefault = true)
    void fromUpdateFormByCustomerToAccount(UpdateByCustomerForm updateByCustomerForm, @MappingTarget Account account);

    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "wardId", target = "ward.id")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "sex", target = "sex")
    Customer fromCreateFormToEntity(CreateCustomerForm createCustomerForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "wardId", target = "ward.id")
    @Mapping(source = "sex", target = "sex")
    @BeanMapping(ignoreByDefault = true)
    void fromUpdateFormToEntity(UpdateCustomerForm updateCustomerForm, @MappingTarget Customer customer);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "wardId", target = "ward.id")
    @Mapping(source = "sex", target = "sex")
    @BeanMapping(ignoreByDefault = true)
    void fromUpdateFormByCustomerToCustomer(UpdateByCustomerForm updateByCustomerForm, @MappingTarget Customer customer);

    @IterableMapping(elementTargetType = CustomerAdminDto.class)
    List<CustomerAdminDto> fromAdminEntityListToDtoList(List<Customer> customerList);

    @IterableMapping(elementTargetType = CustomerClientDto.class)
    List<CustomerClientDto> fromClientEntityListToDtoList(List<Customer> customerList);

    @IterableMapping(elementTargetType = CustomerAutoCompleteDto.class)
    List<CustomerAutoCompleteDto> fromAutoCompleteEntityListToDtoList(List<Customer> customerList);
}
