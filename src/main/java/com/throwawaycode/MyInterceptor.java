package com.throwawaycode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MyInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MyInterceptor.class);


    @Value("${what.to.say:IM_DEFINED_IN_THE_INTERCEPTOR_ANNOTATION}")
    private String whatToSay;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LOG.info("whatToSay={}", whatToSay);
        return super.preHandle(request, response, handler);
    }
}

