package com.tang.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

/**
 * 日志实体
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午1:56
 **/
@Getter
@Setter
@Builder
@ApiModel
public class ServiceLog {
    @ApiModelProperty(value = "日志id", example = "1")
    private int id;
    @ApiModelProperty(value = "所属模块", example = "login")
    private String module;
    @ApiModelProperty(value = "操作名称", example = "登录")
    private String operate;

    @Tolerate
    public ServiceLog() {
    }
}