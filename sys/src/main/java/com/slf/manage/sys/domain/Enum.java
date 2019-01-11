package com.slf.manage.sys.domain;

import com.sun.istack.internal.NotNull;
import lombok.Getter;

import javax.persistence.*;

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
