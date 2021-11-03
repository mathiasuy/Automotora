package com.mathiasuy.automotora.filters;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.util.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mathiasuy.automotora.utils.Constants;

public class Filter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String requestId = request.getHeader(Constants.REQUEST_ID_HEADER);
            if (StringUtils.isNullOrEmpty(requestId)) {
            	requestId =  UUID.randomUUID().toString().toUpperCase().replace("-", "");
                response.addHeader(Constants.REQUEST_ID_HEADER, requestId);
            }
            MDC.put(Constants.REQUEST_ID_HEADER, requestId);
            filterChain.doFilter(request,response);
        } finally {
            MDC.remove(Constants.REQUEST_ID_HEADER);
        }
    }

}
