package com.javainuse.security.filters;

import com.javainuse.entities.User;
import com.javainuse.exceptions.ForbiddenException;
import com.javainuse.exceptions.JwtException;
import com.javainuse.repositories.UserRepository;
import com.javainuse.security.routes.RouteValidator;
import com.javainuse.security.jwt.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    private final JwtService jwtService;

    private final UserRepository userRepository;

    public AuthenticationFilter(RouteValidator validator, JwtService jwtService, UserRepository userRepository) {
        super(Config.class);
        this.validator = validator;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            if (validator.isSecured.test(exchange.getRequest())) {
                final ServerHttpRequest request = exchange.getRequest();
                final HttpHeaders headers = request.getHeaders();

                if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new ForbiddenException("missing authorization header");
                }

                String authHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    throw new ForbiddenException("missing token");
                }

                try {
                    String token = authHeader.substring(7);
                    String username = jwtService.extractUsername(token);
                    User userDetails = userRepository.findByUsername(username).orElseThrow(() -> new ForbiddenException("User not found"));

                    Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
                    String allowedRoles = (String) route.getMetadata().get("allowedRoles");

                    if (allowedRoles != null) {
                        Collection<? extends GrantedAuthority> userAuthorities = userDetails.getAuthorities();
                        List<String> allowedRolesList = Arrays.asList(allowedRoles.split(","));

                        if (!userAuthorities.stream().anyMatch(authority -> allowedRolesList.contains(authority.getAuthority()))) {
                            throw new JwtException("No role");
                        }
                    }
                    ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                            .header(HttpHeaders.AUTHORIZATION, authHeader)
                            .build();

                    return chain.filter(exchange.mutate().request(modifiedRequest).build());
                } catch (ExpiredJwtException e) {
                    throw new JwtException("Token is expired");
                } catch (SignatureException e) {
                    throw new JwtException("Invalid token signature");
                } catch (Exception e) {
                    throw new JwtException("No Access");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }

}
