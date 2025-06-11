package com.cathay.exchangeflow.core.cofig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        String requestBody =
                new String(wrappedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);
        logger.info("Request: {} {} Body: {}", request.getMethod(), request.getRequestURI(),
                requestBody);

        filterChain.doFilter(wrappedRequest, wrappedResponse);

        String responseBody =
                new String(wrappedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);

        logger.info("Response: {} Body: {}", response.getStatus(), responseBody);

        wrappedResponse.copyBodyToResponse();
    }
}
