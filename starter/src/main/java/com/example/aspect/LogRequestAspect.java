package com.example.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

@Aspect
@Slf4j
public class LogRequestAspect {

    private static final String DEFAULT_FORMAT = "Request Info: %s, %s, %s";

    private static final String DEFAULT_LEVEL = "INFO";

    private final String level;

    private final String format;

    public LogRequestAspect(String level, String format) {
        this.level = (level == null || level.isEmpty()) ? DEFAULT_LEVEL : level;
        this.format = (format == null || format.isEmpty()) ? DEFAULT_FORMAT : format;
    }

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
        String logMessage = String.format(format, "Request Method: " + request.getMethod(),
                "Request URL: " + request.getRequestURL(),
                "Request Headers: " + getHeadersInfo(request));
        log(logMessage);
    }

    private void logResponseDetails(HttpServletResponse response, long duration) {
        String logMessage = String.format(format, "Response Status: " + response.getStatus(),
                "Response Headers: " + getHeadersInfo(response),
                "Request Duration: " + duration + " ms");
        log(logMessage);
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

    private void log(String message) {
        switch (level.toUpperCase()) {
            case "WARN":
                log.warn(message);
                break;
            case "ERROR":
                log.error(message);
                break;
            default:
                log.info(message);
                break;
        }
    }
}
