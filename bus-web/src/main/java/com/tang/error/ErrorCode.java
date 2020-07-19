package com.tang.error;

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
    SUCCESS(0),
    /**
     * 失败
     */
    FAIL(1),
    /**
     * 错误
     */
    ERROR(3);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int value() {
        return code;
    }
}