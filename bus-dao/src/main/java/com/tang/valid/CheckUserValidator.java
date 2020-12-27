package com.tang.valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验注解的实现类
 *
 * @author he
 * @since 2020-12.27-11:56
 */
@Slf4j
public class CheckUserValidator implements ConstraintValidator<CheckUser, Object> {
    private String pwd;

    @Override
    public void initialize(CheckUser constraintAnnotation) {
        pwd = constraintAnnotation.pwd();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        BeanWrapper wrapper = new BeanWrapperImpl(value);
        String target = (String) wrapper.getPropertyValue(pwd);
        if (target.length() < 9) {
            return false;
        }
        return true;
    }
}