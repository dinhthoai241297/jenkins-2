package com.digi.api.validation.impl;

import com.digi.api.constant.DigiConstant;
import com.digi.api.validation.Gender;
import com.digi.api.validation.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class StatusValidation  implements ConstraintValidator<Status, Integer> {
    private boolean allowNull;

    @Override
    public void initialize(Status constraintAnnotation) {
        allowNull = constraintAnnotation.allowNull();
    }
    @Override
    public boolean isValid(Integer status, ConstraintValidatorContext constraintValidatorContext) {
        if(status == null && allowNull){
            return true;
        }
        if(!Objects.equals(status, DigiConstant.STATUS_ACTIVE)
                && !Objects.equals(status, DigiConstant.STATUS_LOCK)
                && !Objects.equals(status, DigiConstant.STATUS_DELETE)
                && !Objects.equals(status, DigiConstant.STATUS_PENDING)){
            return false;
        }
        return true;
    }
}
