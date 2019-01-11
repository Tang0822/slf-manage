package com.slf.manage.sys.domain;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author jftang3
 */
@Entity
@Getter
@Setter
@Table(name = "t_user")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String realName;

    private Building building;

    private Floor floor;

    private Enum room;

    private String mail;

    private String phone;

    private Integer isAble;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}
