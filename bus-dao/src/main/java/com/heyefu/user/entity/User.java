package com.heyefu.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午1:56
 **/
@Getter
@Setter
@Builder
@ApiModel
public class User {
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("密码")
    private String password;
    @Tolerate
    public User() {
    }
}