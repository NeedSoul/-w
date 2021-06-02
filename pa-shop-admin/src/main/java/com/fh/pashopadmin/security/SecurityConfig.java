package com.fh.pashopadmin.security;

import com.fh.pashopadmin.config.TokenCommons;
import com.fh.pashopadmin.entity.UmsAdmin;
import com.fh.pashopadmin.service.IUmsAdminService;
import com.fh.pashopadmin.service.IUmsPermissionService;
import com.fh.pashopadmin.service.IUmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.util.List;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IUmsPermissionService permissionService;

    @Autowired
    private IUmsRoleService roleService;

    @Autowired
    private IUmsAdminService adminService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;


    /*Http安全配置*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/loginUser/**","/uploadUmsImage/**","/userPhoto/**").permitAll() //要放开的请求
        .anyRequest().authenticated();  // 其他的都要登录认证\]
        // 认证前需要执行的方法
        http.addFilterBefore( jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 禁用 csrf 让post请求通过
        http.csrf().disable();
        // 关闭sission
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
        //当登陆认证失败时或者没有权限访问时要返回自定的json数据
        http.exceptionHandling().accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);

    }
    /* 用户认证配置*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* 指定用户认证时，默认从哪里获取认证用户信息*/
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
     @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }
    @Override
    @Bean    // 用户的身份认证
    protected UserDetailsService userDetailsService() {
         return username->{
             String userKey = TokenCommons.getUserKey(username, "userKey");
             String roleKey = TokenCommons.getUserKey(username, "roleKey");
             String permissionKey = TokenCommons.getUserKey(username, "permissionKey");
             UmsAdmin user = null;
             if(redisTemplate.hasKey(userKey)){
                 user = (UmsAdmin) redisTemplate.opsForValue().get(userKey);
             }else {
                 user = adminService.getUser(username);
                 redisTemplate.opsForValue().set(userKey,user);
             }
             if (user != null) {
                 Long userid= user.getId();
                 List<String >  userPermissionKey = null;
                 List<String >   userRoleKey = null;

                 if(redisTemplate.hasKey(permissionKey)){
                     userPermissionKey  = (List<String>) redisTemplate.opsForValue().get(permissionKey);
                 }else {
                     userPermissionKey =  permissionService.getUserPermissionKey(userid);
                     redisTemplate.opsForValue().set(permissionKey,userPermissionKey);
                 }
                 if(redisTemplate.hasKey(roleKey)){
                     userRoleKey  = (List<String>) redisTemplate.opsForValue().get(roleKey);
                 }else {
                     userRoleKey =  roleService.getUserRoleKey(userid);
                     redisTemplate.opsForValue().set(roleKey,userRoleKey);
                 }
                 return new AdminUserDetails(userPermissionKey,userRoleKey,user);
             }else{
                 throw new UsernameNotFoundException("该用户不存在");
             }

        };
    }

    @Bean    // 密码的加密方式
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override  //用来处理身份验证请求，并返回一个Authentication对象
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
