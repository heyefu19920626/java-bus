package com.tang.enums.handle;

import com.tang.enums.BaseValueEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * @author he
 * @since 2020-12.26-22:37
 */
@SuppressWarnings("all")
class StringToEnumConverter<T extends BaseValueEnum> implements Converter<String, T> {
    private final Class<T> targetType;

    public StringToEnumConverter(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T convert(String source) {
        if (StringUtils.isBlank(source)) {
            return (T) targetType.getEnumConstants()[0].getDefault();
        }
        for (T enumObj : targetType.getEnumConstants()) {
            if (source.equals(enumObj.getDesc())) {
                return enumObj;
            }
        }
        return (T) targetType.getEnumConstants()[0].getDefault();
    }
}