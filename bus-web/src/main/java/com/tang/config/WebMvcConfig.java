package com.tang.config;

import com.tang.enums.handle.EnumConvertFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * web配置器
 *
 * @author tang
 * @since 2020-11.27-01:29
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private EnumConvertFactory enumConvertFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 注册spring的枚举转换
        registry.addConverterFactory(enumConvertFactory);
    }
}
