package com.digi.api.mapper;

import com.digi.api.dto.account.AccountAdminDto;
import com.digi.api.dto.account.AccountDto;
import com.digi.api.form.account.CreateAccountAdminForm;
import com.digi.api.form.account.UpdateAccountAdminForm;
import com.digi.api.form.account.UpdateProfileAdminForm;
import com.digi.api.storage.model.Account;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "avatarPath", target = "avatarPath")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    Account fromCreateAccountAdminFormToAdmin(CreateAccountAdminForm createAccountAdminForm);


    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    void mappingFormUpdateAdminToEntity(UpdateAccountAdminForm updateAccountAdminForm,@MappingTarget Account account);

    @Mapping(source = "fullName", target = "fullName")
    void mappingFormUpdateProfileToEntity(UpdateProfileAdminForm updateProfileAdminForm, @MappingTarget Account account);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "group", target = "group")
    @Mapping(source = "lastLogin", target = "lastLogin")
    @Mapping(source = "avatarPath", target = "avatar")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    @Mapping(source = "isSuperAdmin", target = "isSuperAdmin")
    AccountDto fromEntityToAccountDto(Account account);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "group", target = "group")
    @Mapping(source = "lastLogin", target = "lastLogin")
    @Mapping(source = "avatarPath", target = "avatar")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "lang", target = "lang")
    AccountAdminDto fromEntityToAccountAdminDto(Account account);

    @IterableMapping(elementTargetType = AccountAdminDto.class)
    List<AccountAdminDto> fromEntityListToDtoList(List<Account> content);
}
