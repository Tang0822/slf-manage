package com.slf.manage.sys.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Integer id;

    private String userName;

    private String password;

    private String realName;

    private Integer building;

    private Integer floor;

    private Integer room;

    private Integer group;

    private String mail;

    private String phone;

    private Integer isAble;

}
