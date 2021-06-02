package com.fh.pashopadmin.security;

import com.fh.pashopadmin.config.ProcessTokenUtils;
import com.fh.pashopadmin.config.TokenCommons;
import com.fh.result.ResultObject;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private ProcessTokenUtils processTokenUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       /* 从request拿出token */
        String toKen = httpServletRequest.getHeader("Authorization-token");
            /* 判断token是否存在*/
        if (toKen !=null){
            /* token存在就判断 是否有效 */
            ResultObject resultObject = processTokenUtils.resolverToken(toKen);
            if(resultObject.getCode()==200){
                /* 判断token是否超时*/
                Claims claims = (Claims) resultObject.getData();
                String username = (String) claims.get("username");
                String redisKey = TokenCommons.getTokenKey(username, toKen, httpServletRequest);
                Boolean aBoolean = redisTemplate.hasKey(redisKey);
                if (redisTemplate.hasKey(redisKey)  && SecurityContextHolder.getContext().getAuthentication() == null){
                    /* 把用户登录认证交给Spring Security去管理  Security 会判断 Authentication中是否想要的对象 来进行判断*/
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails ,null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

}
