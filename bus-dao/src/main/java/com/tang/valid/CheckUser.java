package com.tang.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义的校验注解
 *
 * 使用时好像必须带有分组？
 *
 * @author he
 * @since 2020-12.27-11:52
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckUserValidator.class)
@Repeatable(CheckUser.List.class)
public @interface CheckUser {
    String pwd() default "pwd";

    String message() default "密码长度不能小于9";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @interface List {
        CheckUser[] value();
    }
}