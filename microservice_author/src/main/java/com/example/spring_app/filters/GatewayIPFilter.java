package com.example.spring_app.filters;


import com.example.spring_app.config.GatewayProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
@Component
public class GatewayIPFilter extends GenericFilterBean {

    private final GatewayProperties gatewayProperties;

    public GatewayIPFilter(GatewayProperties gatewayProperties) {
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String remoteAddr = request.getRemoteAddr();
        if (remoteAddr == null ||  gatewayProperties.getAddress().equals(remoteAddr)) {
            chain.doFilter(request, response);
        }

    }

}