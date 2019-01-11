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
@Table(name = "t_group_permission")
public class GroupPermission {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="g_id")
    private Group group;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="p_id")
    private Permission permission;
}
