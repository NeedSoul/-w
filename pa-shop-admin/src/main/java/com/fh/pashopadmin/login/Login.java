package com.fh.pashopadmin.login;


import com.fh.pashopadmin.config.ProcessTokenUtils;
import com.fh.pashopadmin.config.TokenCommons;
import com.fh.pashopadmin.entity.UmsAdmin;
import com.fh.pashopadmin.security.AdminUserDetails;
import com.fh.pashopadmin.service.IUmsAdminService;

import com.fh.result.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
 import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/loginUser")
public class Login {

@Autowired
private IUmsAdminService adminService;

    @Autowired
    private ProcessTokenUtils processTokenUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResultObject login(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
      /*  if (StringUtils.isBlank(username)  || StringUtils.isBlank(password)){
            return ResultObject.error(ResponseEnum.IS_NULL);
        }
       UmsAdmin  user= adminService.getUser(username);
        if(user == null){
            return  ResultObject.error(ResponseEnum.ACCOUNT_NOT_EXIST);
        }
        if (!user.getPassword().equals(Md5Util.md5(password))){
            return ResultObject.error(ResponseEnum.PASSWORD_ERROR);
        }*/

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("判断密码"+!passwordEncoder.matches(password,userDetails.getPassword()));
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            System.out.println(password);
            throw new BadCredentialsException("用户密码不正确");
        }

       /* UsernamePasswordAuthenticationToken  authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
*/
        AdminUserDetails adminUserDetails = (AdminUserDetails) userDetails;
        UmsAdmin user = adminUserDetails.getUmsAdmin();

        /*成功 发送请求 token*/
        String token = processTokenUtils.createToken(user);

        String queryTokenKey = TokenCommons.getQueryTokenKey(user.getUsername(), request);
        Set keys = redisTemplate.keys(queryTokenKey);

        if (keys.size()>0){
            Long delete = redisTemplate.delete(keys);
        }

        redisTemplate.opsForValue().set(TokenCommons.getTokenKey(user.getUsername(), token, request),System.currentTimeMillis(),TokenCommons.token_time, TimeUnit.MINUTES);
        return ResultObject.success(token);

    }

}
