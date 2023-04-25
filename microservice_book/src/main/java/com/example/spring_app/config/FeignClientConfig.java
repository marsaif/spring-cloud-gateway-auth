package com.example.spring_app.config;

import com.example.spring_app.interceptors.FeignJwtInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor feignJwtInterceptor() {
        return new FeignJwtInterceptor();
    }
}