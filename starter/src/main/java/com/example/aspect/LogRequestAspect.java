package com.example.aspect;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

@Aspect
@Slf4j
@Component
public class LogRequestAspect {

    @Pointcut("@annotation(com.example.annotations.LogRequest)")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        long startTime = System.currentTimeMillis();
        logRequestDetails(request);

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        logResponseDetails(response, endTime - startTime);

        return result;
    }

    private void logRequestDetails(HttpServletRequest request) {
        log.info("Request Method: {}", request.getMethod());
        log.info("Request URL: {}", request.getRequestURL());
        log.info("Request Headers: {}", getHeadersInfo(request));
    }

    private void logResponseDetails(HttpServletResponse response, long duration) {
        log.info("Response Status: {}", response.getStatus());
        log.info("Response Headers: {}", getHeadersInfo(response));
        log.info("Request Duration: {} ms", duration);
    }

    private String getHeadersInfo(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headers = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.append(headerName).append(": ").append(request.getHeader(headerName)).append(", ");
        }
        return headers.toString();
    }

    private String getHeadersInfo(HttpServletResponse response) {
        StringBuilder headers = new StringBuilder();
        for (String headerName : response.getHeaderNames()) {
            headers.append(headerName).append(": ").append(response.getHeader(headerName)).append(", ");
        }
        return headers.toString();
    }
}
