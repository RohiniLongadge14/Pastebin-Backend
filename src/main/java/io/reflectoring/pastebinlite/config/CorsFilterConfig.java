package io.reflectoring.pastebinlite.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class CorsFilterConfig {

    @Bean
    public OncePerRequestFilter corsFilter() {
        return new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    FilterChain filterChain
            ) throws ServletException, IOException {

                response.setHeader(
                        "Access-Control-Allow-Origin",
                        "https://pastebin-frontend-780wwa7vv-rohinis-projects-c95122e8.vercel.app"
                );
                response.setHeader(
                        "Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS"
                );
                response.setHeader(
                        "Access-Control-Allow-Headers",
                        "Content-Type, Authorization"
                );
                response.setHeader(
                        "Access-Control-Max-Age",
                        "3600"
                );

                // 👇 VERY IMPORTANT
                if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    return;
                }

                filterChain.doFilter(request, response);
            }
        };
    }
}
