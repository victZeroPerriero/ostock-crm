package com.vgl.licenses.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;

@Component
public class Interceptor implements HandlerInterceptor {
    private final Logger LOGGER = LoggerFactory.getLogger(Interceptor.class.getName());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("start interceptor");
        if (request.getHeader("Authorization").equals("test")){
           Enumeration<String> headerNames = request.getHeaderNames();
         while (headerNames.hasMoreElements()){
                String name = headerNames.nextElement();
                LOGGER.info("header name {}", name);

            }
            LOGGER.info("interceptor sending request before controller {}", request.getRequestURI());
        } else {
            throw new Exception("Valid.NOK");
        }
        return true;
    }
}
