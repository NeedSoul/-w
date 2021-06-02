package com.fh.pashopadmin.security;

import com.alibaba.fastjson.JSON;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultObject;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint  implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if(e instanceof BadCredentialsException){
            response.getWriter().println(JSON.toJSONString(ResultObject.error(ResponseEnum.PASSWORD_ERROR)));
        }else if(e instanceof UsernameNotFoundException){
            response.getWriter().println(JSON.toJSONString(ResultObject.error(ResponseEnum.ACCOUNT_NOT_EXIST)));
        }else{
            response.getWriter().println(JSON.toJSONString(ResultObject.error(ResponseEnum.TOKEN_INVALID)));
        }

        response.getWriter().flush();

    }
}
