package com.slf.manage.sys.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author jftang3
 */
@Entity
@Getter
@Setter
@Table(name = "t_floor")
public class Floor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private String content;

    private Date createTime;

    private Date updateTime;
}
