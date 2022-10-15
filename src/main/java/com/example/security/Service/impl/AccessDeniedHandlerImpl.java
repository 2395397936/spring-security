package com.example.security.Service.impl;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.Errors;
import com.example.common.utils.R;
import com.example.security.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R result =  R.fail(Errors.AccessDeniedError.getCode(), Errors.AccessDeniedError.getMsg());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);

    }
}