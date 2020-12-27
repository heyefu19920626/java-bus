package com.tang.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tang.enums.UserType;
import com.tang.enums.handle.EnumCovert;
import com.tang.util.EncryptUtil;
import com.tang.valid.AddValid;
import com.tang.valid.CheckUser;
import com.tang.valid.UpdateValid;
import io.swagger.annotations.ApiModelProperty;
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
@CheckUser(pwd = "pwd", groups = {AddValid.class})
@CheckUser(pwd = "name", groups = {AddValid.class}, message = "用户名长度不能小于9")
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

    public String getPwd() {
        return EncryptUtil.decrypt(pwd);
    }

    public void setPwd(String pwd) {
        this.pwd = EncryptUtil.encrypt(pwd);
    }

    /**
     * mybatis使用
     *
     * @param pwd 密文
     */
    public void setPwdCiphertext(String pwd) {
        this.pwd = pwd;
    }

    /**
     * mybatis使用
     * <p>
     * 使用@JsonIgnore会使返回前台的属性不包含这个，也可以使swagger的json参数示例中不包含这个
     * 使用@ApiModelProperty(hidden = true)可以使swagger的json和表单参数都不包含这个
     *
     * @return 密文
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public String getPwdCiphertext() {
        return pwd;
    }
}