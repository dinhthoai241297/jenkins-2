package com.digi.api.constant;


import com.digi.api.utils.ConfigurationService;

public class DigiConstant {


    public static final String URL_QRCODE_ACCESS = "https://hqqrcode.developteam.net"  ;
    public static final String ADMIN_USERNAME = "admin";

    public static final String ROOT_DIRECTORY =  ConfigurationService.getInstance().getString("file.upload-dir","/tmp/upload");

    public static final Integer USER_KIND_ADMIN = 1;
    public static final Integer USER_KIND_CUSTOMER = 2;

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_PENDING = 0;
    public static final Integer STATUS_LOCK = -1;
    public static final Integer STATUS_DELETE = -2;

    public static final Integer GROUP_KIND_SUPER_ADMIN = 1;
    public static final Integer GROUP_KIND_ADMIN = 2;
    public static final Integer GROUP_KIND_CUSTOMER = 3;

    public static final Integer MAX_ATTEMPT_FORGET_PWD = 5;
    public static final Integer MAX_TIME_FORGET_PWD = 5 * 60 * 1000; //5 minutes
    public static final Integer MAX_ATTEMPT_LOGIN = 5;


    public static final String PROVINCE_KIND_PROVINCE="PROVINCE_KIND_PROVINCE";
    public static final String PROVINCE_KIND_DISTRICT="PROVINCE_KIND_DISTRICT";
    public static final String PROVINCE_KIND_WARD="PROVINCE_KIND_WARD";

    public static final Integer GENDER_MALE = 1;
    public static final Integer GENDER_FEMALE = 2;
    public static final Integer GENDER_OTHER = 3;

    private DigiConstant(){
        throw new IllegalStateException("Utility class");
    }

}
