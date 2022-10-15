package com.example.security.Service.impl;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.Errors;
import com.example.common.utils.R;
import com.example.security.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R result =  R.fail(Errors.AuthenticationError.getCode(), Errors.AuthenticationError.getMsg());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
