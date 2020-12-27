package com.tang.handle;

import com.tang.response.ErrorCode;
import com.tang.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * controller全局异常处理器
 *
 * @author tang
 */
@RestControllerAdvice
@Slf4j
@SuppressWarnings("all")
public class GlobalExceptionHandle {
    @Resource
    private HttpServletRequest request;

    @ExceptionHandler(BindException.class)
    public RestResponse<String> handleException(BindException e) {
        String name;
        String message;
        FieldError fieldError = e.getFieldError();
        if (fieldError != null) {
            name = fieldError.getField();
            message = fieldError.getDefaultMessage();
        } else {
            // 自定义的校验无法由于放在类上的，无法获取具体的字段名
            List<ObjectError> allErrors = e.getAllErrors();
            name = allErrors.get(0).getObjectName();
            message = allErrors.get(0).getDefaultMessage();
        }
        log.error("{} {} {}.", request.getRequestURI(), name, message);
        return new RestResponse<>(ErrorCode.PARAM_ERROR, name + ":" + message);
    }

    /**
     * 拦截json的校验异常
     *
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<String> handleException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        log.error("{} {} {}.", request.getRequestURI(), fieldError.getField(), fieldError.getDefaultMessage());
        return new RestResponse<>(ErrorCode.PARAM_ERROR, fieldError.getField() + ":" + fieldError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public RestResponse<String> handleException(Exception e) {
        log.error("system error", e);
        return new RestResponse<>(ErrorCode.ERROR, "请求异常");
    }
}