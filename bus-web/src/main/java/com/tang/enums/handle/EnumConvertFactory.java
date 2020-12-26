package com.tang.enums.handle;

import com.tang.enums.BaseValueEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * spring接受参数枚举转换
 * <p>
 * 这里配置的对所有的都起效，不过要先在WebMvcConfigurer注册
 *
 * @author tang
 * @since 2020-11.27-01:25
 */
@Component
@SuppressWarnings("all")
public class EnumConvertFactory implements ConverterFactory<String, BaseValueEnum> {
    @Override
    public <T extends BaseValueEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumConverter<>(targetType);
    }
}