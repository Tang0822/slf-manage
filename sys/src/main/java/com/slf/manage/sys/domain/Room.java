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
@Table(name = "t_room")
public class Room {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer code;

    private String sign;

    private String content;

}
