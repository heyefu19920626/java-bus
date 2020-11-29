package com.tang.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户类型
 *
 * @author tang
 * @since 2020-11.27-00:48
 */
@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
public enum UserType implements  BaseValueEnum{
    /**
     * 超级管理员
     */
    SUPER_ADMIN(0),
    /**
     * 管理员
     */
    ADMIN(1),
    /**
     * 成员
     */
    MEMBER(2);

    private int value;

    UserType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    public static UserType getUserType(int value) {
        for (UserType userType : UserType.values()) {
            if (userType.getValue() == value) {
                return userType;
            }
        }
        return MEMBER;
    }

}