package com.fh.pashopadmin.config;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fh.pashopadmin.entity.UmsAdmin;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultObject;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 尚峰
 * @company 飞狐教育
 * @create 2021-05-16 15:14
 */
@Component
public class ProcessTokenUtils {

    @Value("${jwt.token.signature}")
    private String signature;
    @Value("${jwt.token.alg}")
    private String alg;
    @Value("${jwt.token.type}")
    private String type;
    @Value("${jwt.token.exptime}")
    private Long exptime;

    public String createToken(UmsAdmin admin) {
        String token = "";
        //jwt的组成部分:
        //（1）加密的方式类型头部信息
        Map<String, Object> headMap = new HashMap<>();
        headMap.put("alg", alg);
        headMap.put("type", type);
        //（2）有效载荷
        Map<String, Object> payLoadMap = new HashMap<>();
        payLoadMap.put("username", admin.getUsername());
        payLoadMap.put("id", admin.getId());
        payLoadMap.put("nickName", admin.getNickName());
        //（3）签名值
        //失效时间
        Long expTime = System.currentTimeMillis()+exptime;
        token = Jwts.builder()
                .setHeader(headMap)
                .setClaims(payLoadMap)
                .setExpiration(new Date(expTime))
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return token;
    }

    public ResultObject resolverToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            //负载数据
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(signature)
                        .parseClaimsJws(token)
                        .getBody();
                System.out.println(claims.get("username"));
                return ResultObject.success(claims);
            } catch (SignatureException e) {
                System.out.println("无效的签名");
            } catch (ExpiredJwtException e) {
                System.out.println("token失效");
            } catch (Exception e) {
                System.out.println("token值字符串不符合规则");
            }
        }
        return  ResultObject.error(ResponseEnum.TOKEN_INVALID);
    }

}
