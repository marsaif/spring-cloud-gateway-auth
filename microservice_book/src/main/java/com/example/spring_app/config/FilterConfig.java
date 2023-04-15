package com.example.spring_app.config;

import com.example.spring_app.filters.GatewayIPFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final GatewayProperties gatewayProperties;

    public FilterConfig(GatewayProperties gatewayProperties) {
        this.gatewayProperties = gatewayProperties;
    }


    @Bean
    public FilterRegistrationBean<GatewayIPFilter> gatewayIPFilter() {
        FilterRegistrationBean<GatewayIPFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new GatewayIPFilter(gatewayProperties));
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}