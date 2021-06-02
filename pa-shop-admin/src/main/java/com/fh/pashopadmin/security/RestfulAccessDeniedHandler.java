package com.fh.pashopadmin.security;

import com.alibaba.fastjson.JSON;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestfulAccessDeniedHandler  implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSONString(ResultObject.error(ResponseEnum.PERMISSION_INVALID)));
        response.getWriter().flush();

    }
}
