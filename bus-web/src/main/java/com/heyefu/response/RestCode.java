package com.heyefu.response;

import com.heyefu.error.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 响应的封装
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午9:56
 **/
@ApiModel
@Getter
@Setter
public class RestCode {
    @ApiModelProperty("错误码")
    private int code;
    @ApiModelProperty("错误消息")
    private String msg;
    @ApiModelProperty("数据")
    private Object data;

    public RestCode(ErrorCode code, Object data) {
        this.code = code.value();
        this.data = data;
    }

    public RestCode(ErrorCode code, String msg, Object data) {
        this.code = code.value();
        this.msg = msg;
        this.data = data;
    }
}