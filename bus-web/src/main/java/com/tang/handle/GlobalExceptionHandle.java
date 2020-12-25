package com.tang.handle;

import com.tang.response.ErrorCode;
import com.tang.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * controller全局异常处理器
 *
 * @author tang
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @Resource
    private HttpServletRequest request;

    @ExceptionHandler(BindException.class)
    public RestResponse<String> handleException(BindException e) {
        log.error("{} request param error.", request.getRequestURI(), e);
        FieldError fieldError = e.getFieldErrors().get(0);
        return new RestResponse<>(ErrorCode.FAIL, fieldError.getField() + ":" + fieldError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public RestResponse<String> handleException(Exception e) {
        log.error("system error", e);
        return new RestResponse<>(ErrorCode.ERROR, "请求异常");
    }
}