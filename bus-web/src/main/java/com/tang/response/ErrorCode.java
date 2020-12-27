package com.tang.response;

/**
 * 错误码
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午9:55
 **/
public enum ErrorCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(1),
    /**
     * 错误
     */
    ERROR(401),
    /**
     * 参数错误
     */
    PARAM_ERROR(10001);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int value() {
        return code;
    }
}