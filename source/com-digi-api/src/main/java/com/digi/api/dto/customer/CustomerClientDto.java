package com.digi.api.dto.customer;

import com.digi.api.dto.group.GroupDto;
import com.digi.api.dto.province.ProvinceDto;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerClientDto {
    private Long id;
    private Date birthday;
    private Integer deviceType;
    private ProvinceDto provinceDto;
    private ProvinceDto districtDto;
    private ProvinceDto wardDto;
    private Integer sex;
    private Integer kind;
    private String username;
    private String email;
    private String fullName;
    private GroupDto groupDto;
    private String avatarPath;
    private String phone;
    private String lang;
    private String address;
}
