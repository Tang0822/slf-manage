package com.slf.manage.sys.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author jftang3
 */
@Entity
@Getter
@Setter
@Table(name = "t_floor")
public class Floor extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private String content;
}
