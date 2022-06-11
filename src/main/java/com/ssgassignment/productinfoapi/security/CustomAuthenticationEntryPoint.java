package com.ssgassignment.productinfoapi.security;

import com.ssgassignment.productinfoapi.common.exception.ErrorResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final MappingJackson2HttpMessageConverter messageConverter;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        try {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            messageConverter.write(new ErrorResult("UNAUTHORIZED", authException.getMessage()),
                    MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            messageConverter.write(new ErrorResult("JWT_EX", e.getMessage()),
                    MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
        }
    }
}
