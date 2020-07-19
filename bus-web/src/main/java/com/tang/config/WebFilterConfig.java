package com.tang.config;

import com.tang.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 过滤器配置类
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午8:29
 **/
@Configuration
public class WebFilterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter()).addPathPatterns("/**")
                .excludePathPatterns("/webjars/springfox-swagger-ui/**").excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/swagger-ui.html/**", "/img/*", "/css/*", "/js/*").excludePathPatterns("/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}