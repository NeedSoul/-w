package com.fh.pashopadmin.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultObject;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/*@Component
@Order(5)
@Aspect*/
public class LoginAuthentication {

    @Autowired
    private HttpServletRequest  request;

    @Autowired
    private ProcessTokenUtils processTokenUtils;

    @Autowired
    private RedisTemplate  redisTemplate;



    @Around(value = "execution(* com.fh.*.controller.*.*(..))")
    public Object aroundLoginAuth(ProceedingJoinPoint joinPoint){
        Object obj=null;
        try {
            String token = request.getHeader("Authorization-token");
            if(StringUtils.isBlank(token)){
                obj= ResultObject.error(ResponseEnum.TOKEN_INVALID);
                return  obj;
            }
            ResultObject resultObject= processTokenUtils.resolverToken(token);
            if(resultObject.getCode() != 200){
                return resultObject;
            }
            Claims claims = (Claims) resultObject.getData();
            String username = (String) claims.get("username");
            String tokenKey = TokenCommons.getTokenKey(username,token,request);
            if(!redisTemplate.hasKey(tokenKey)){
               return ResultObject.error(ResponseEnum.TOKEN_INVALID);
            }
            redisTemplate.expire(tokenKey,TokenCommons.token_time, TimeUnit.MINUTES);

            obj=joinPoint.proceed();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }

}
