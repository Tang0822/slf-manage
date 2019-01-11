package com.slf.manage.sys.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author jftang3
 */
@Entity
@Getter
@Table(name = "t_enum")
public class Enum {

    private String sign;

    private String code;

    private String content;

}
