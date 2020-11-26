package com.tang.config;

import com.tang.enums.UserType;
import com.tang.enums.handle.CodeEnumTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.stereotype.Component;

/**
 * mybatis的转换注册
 *
 * @author tang
 * @since 2020-11.27-01:44
 */
// @Component
@Slf4j
public class RegisterEnumHandlerConfig implements ConfigurationCustomizer {
    @Override
    public void customize(Configuration configuration) {
        log.info("start registry mybatis enum handle");
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        typeHandlerRegistry.register(UserType.class, CodeEnumTypeHandler.class);
    }
}
