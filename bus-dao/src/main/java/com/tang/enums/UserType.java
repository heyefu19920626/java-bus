package com.tang.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 用户类型
 * <p>
 * '@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
 * 在类上使用该注解，可以选择返回给前端的格式
 *
 * @author tang
 * @since 2020-11.27-00:48
 */
public enum UserType implements BaseValueEnum<UserType> {
    /**
     * 超级管理员
     */
    SUPER_ADMIN(0, "超级管理员"),
    /**
     * 管理员
     */
    ADMIN(1, "管理员"),
    /**
     * 成员
     */
    MEMBER(2, "会员");

    private final int value;

    private final String name;

    UserType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public UserType getDefault() {
        return MEMBER;
    }

    @Override
    public String getDesc() {
        return name;
    }

    /**
     * 返回给前台的枚举值
     * <p>
     * '@JsonValue 使用该注解,可以将枚举值返回给前端的时候,用该函数转化json
     *
     * @return 名称
     */
    @JsonValue
    public String getName() {
        return this.name;
    }

    /**
     * '@JsonCreator 将前端传入的json转化为枚举值，只有json类型的前端值才可以
     *
     * @param value 值
     * @return 枚举值
     */
    public static UserType covertUserType(int value) {
        for (UserType userType : UserType.values()) {
            if (userType.getValue() == value) {
                return userType;
            }
        }
        return MEMBER;
    }

    @JsonCreator
    public static UserType covertUserType(String name) {
        for (UserType userType : UserType.values()) {
            if (userType.getName().equals(name)) {
                return userType;
            }
        }
        return MEMBER;
    }

    @Override
    public String toString() {
        return name;
    }
}