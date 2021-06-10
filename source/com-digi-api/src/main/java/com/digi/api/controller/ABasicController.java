package com.digi.api.controller;

import com.digi.api.constant.DigiConstant;
import com.digi.api.intercepter.MyAuthentication;
import com.digi.api.jwt.UserJwt;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class ABasicController {

    public long getCurrentUserId(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        MyAuthentication authentication = (MyAuthentication)securityContext.getAuthentication();
        return authentication.getJwtUser().getAccountId().longValue();
    }

    public UserJwt getSessionFromToken(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        MyAuthentication authentication = (MyAuthentication)securityContext.getAuthentication();
        return authentication.getJwtUser();
    }

    public boolean isAdmin(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        MyAuthentication authentication = (MyAuthentication)securityContext.getAuthentication();
        return Objects.equals(authentication.getJwtUser().getUserKind(), DigiConstant.USER_KIND_ADMIN);
    }

    public boolean isSuperAdmin(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        MyAuthentication authentication = (MyAuthentication)securityContext.getAuthentication();
        return Objects.equals(authentication.getJwtUser().getUserKind(), DigiConstant.USER_KIND_ADMIN) && authentication.getJwtUser().getIsSuperAdmin();
    }
}

