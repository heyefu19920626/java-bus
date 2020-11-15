package com.tang.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-11-15 17:03:36
 */
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = 313871532794545014L;

    private Integer id;

    private String name;

    private String pwd;
}