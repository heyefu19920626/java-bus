package com.tang.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * security的配置类
 *
 * @author he
 * @since 2021-01.10-00:25
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("root").password(
            passwordEncoder().encode("123456")).roles("ADMIN").and().withUser("test").password(
            passwordEncoder().encode("123456")).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // spring提供的加密工具，可快速实现加密加盐
        return new BCryptPasswordEncoder();
    }
}