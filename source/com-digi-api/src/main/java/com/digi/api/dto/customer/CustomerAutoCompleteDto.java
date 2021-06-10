package com.digi.api.dto.customer;

import lombok.Data;

@Data
public class CustomerAutoCompleteDto {
    private Long id;
    private String email;
    private String fullName;
    private String avatarPath;
    private String phone;
}
