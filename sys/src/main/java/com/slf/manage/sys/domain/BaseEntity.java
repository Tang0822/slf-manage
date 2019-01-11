package com.slf.manage.sys.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    private Date createTime;

    private Date updateTime;
}