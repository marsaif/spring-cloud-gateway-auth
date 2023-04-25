package com.example.spring_app.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class FeignJwtInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            String jwtToken = (String) requestAttributes.getAttribute(JwtInterceptor.JWT_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
            if (jwtToken != null) {
                template.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
            }
        }
    }
}