package com.slf.manage.sys.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jftang3
 */
@Entity
@Getter
@Setter
@Table(name = "t_enum")
public class Room {

    @Id
    private Integer code;

    private String sign;

    private String content;

}
