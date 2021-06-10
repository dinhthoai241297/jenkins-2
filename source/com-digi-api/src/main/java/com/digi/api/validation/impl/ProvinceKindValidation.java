package com.digi.api.validation.impl;

import com.digi.api.constant.DigiConstant;
import com.digi.api.validation.Gender;
import com.digi.api.validation.ProvinceKind;
import com.digi.api.validation.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ProvinceKindValidation implements ConstraintValidator<ProvinceKind, String> {
    private boolean allowNull;

    @Override
    public void initialize(ProvinceKind constraintAnnotation) {
        allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(String kind, ConstraintValidatorContext constraintValidatorContext) {
        if(kind == null && allowNull){
            return true;
        }
        if(!Objects.equals(kind, DigiConstant.PROVINCE_KIND_PROVINCE)
                && !Objects.equals(kind, DigiConstant.PROVINCE_KIND_WARD)
                && !Objects.equals(kind, DigiConstant.PROVINCE_KIND_DISTRICT)){
            return false;
        }
        return true;
    }
}
