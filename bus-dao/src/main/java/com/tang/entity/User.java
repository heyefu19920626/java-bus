package com.tang.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.tang.enums.UserType;
import com.tang.enums.handle.EnumCovert;
import com.tang.valid.AddValid;
import com.tang.valid.UpdateValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull(groups = UpdateValid.class)
    private Integer id;

    @NotEmpty(groups = AddValid.class)
    private String name;

    @NotEmpty(groups = AddValid.class)
    private String pwd;

    @JSONField(serializeUsing = EnumCovert.class)
    @NotNull(groups = AddValid.class)
    private UserType userType;
}