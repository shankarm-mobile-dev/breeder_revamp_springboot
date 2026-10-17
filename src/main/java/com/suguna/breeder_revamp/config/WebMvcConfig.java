package com.suguna.breeder_revamp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final SingleSessionInterceptor singleSessionInterceptor;

    public WebMvcConfig(SingleSessionInterceptor singleSessionInterceptor) {
        this.singleSessionInterceptor = singleSessionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(singleSessionInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/auth/getUserID",
                        "/api/auth/getUserType",
                        "/api/auth/getLogin",
                        "/api/auth/updateMPIN",
                        "/api/auth/createOTP",
                        "/api/auth/updatePassword",
                        "/api/auth/createLogin",
                        "/api/auth/createSupervisorLogin",
                        "/api/auth/supervisorEmail/**"
                );
    }
}
