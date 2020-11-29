package com.tang.handle;

import com.tang.response.ErrorCode;
import com.tang.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controller全局异常处理器
 *
 * @author tang
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    @ExceptionHandler(Exception.class)
    public RestResponse<String> handleException(Exception e) {
        log.error("system error",e);
        return new RestResponse<>(ErrorCode.ERROR, "请求异常");
    }
}