package com.datadamoa.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> authorize
                .anyRequest().permitAll() // 모든 요청에 대해 인증 없이 접근 허용
            )
            .httpBasic().disable() // 기본 인증 비활성화
            .csrf().disable(); // CSRF 보호 기능 비활성화

        return http.build();
    }
}



