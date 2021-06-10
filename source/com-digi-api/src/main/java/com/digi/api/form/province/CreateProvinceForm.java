package com.digi.api.form.province;

import com.digi.api.validation.ProvinceKind;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateProvinceForm {
    @NotEmpty(message = "Province's name can not be empty")
    private String provinceName;
    private Long parentId;
    @NotEmpty(message = "Province's type can not be empty")
    @ProvinceKind(message = "Kind invalid.", allowNull = false)
    private String kind;
}
