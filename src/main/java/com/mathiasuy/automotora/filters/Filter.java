package com.automotora.filters;

import org.h2.util.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.automotora.utils.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class Filter extends OncePerRequestFilter {

    public static final String CONTEXT = "pasadas";
    private static int pasadas = 0;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setAttribute(CONTEXT,"Pasó por el filtro " + ++pasadas);
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
