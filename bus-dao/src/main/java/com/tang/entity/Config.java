package com.tang.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

/**
 * config表实体
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午1:56
 **/
@Getter
@Setter
@Builder
@ApiModel
public class Config {
    @ApiModelProperty(value = "键名", example = "key")
    private String key;
    @ApiModelProperty(value = "键值", example = "value")
    private String value;

    @Tolerate
    public Config() {
    }
}