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
    @ApiModelProperty("日志id")
    private int id;
    @ApiModelProperty("模块")
    private String module;
    @ApiModelProperty("操作")
    private String operate;

    @Tolerate
    public ServiceLog() {
    }
}