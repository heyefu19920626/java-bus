package com.tang.response;

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
public class RestResponse<T> {
    @ApiModelProperty(value = "错误码", example = "001")
    private int code;
    @ApiModelProperty(value = "错误消息", example = "未认证")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;

    /**
     * 错误的响应,不带数据
     *
     * @param code 错误码
     * @param msg  错误消息
     */
    public RestResponse(ErrorCode code, String msg) {
        this.code = code.value();
        this.msg = msg;
    }

    /**
     * 成功的响应，不带消息，自动填充成功码
     *
     * @param data 响应数据
     */
    public RestResponse(T data) {
        this.code = ErrorCode.SUCCESS.value();
        this.data = data;
    }
}