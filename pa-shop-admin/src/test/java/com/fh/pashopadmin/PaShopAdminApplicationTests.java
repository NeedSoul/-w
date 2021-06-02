package com.fh.pashopadmin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*@SpringBootTest*/
class PaShopAdminApplicationTests {

    @Test
    void contextLoads() {


        double b = 25%2;

        System.out.println(b);

     /*   System.out.println( );*/

     /*    if(new BCryptPasswordEncoder().matches("123456","$2a$10$NLIEwemfvKCquQGnWotQYOKptcyU6P/5ua16MvHaz8NQ1m/KiqOKW")){
            System.out.println("zzz");
        }

        System.out.println(new BCryptPasswordEncoder().encode("123456"));*/

    }
public String testToken(){
        String  token = "";
    Map<String,Object>  headMap= new HashMap<>();
    /* 头部信息 */
    headMap.put("alg","HS256");
    headMap.put("type","JWT");
    /*有效载荷 */
    Map<String, Object> payLoad = new HashMap<>();
payLoad.put("userName","aaaaaa");

Long startTime=System.currentTimeMillis();
Long  endTime = startTime+120000;
    token= Jwts.builder().setHeader(headMap)
            .setClaims(payLoad)
            .setExpiration(new Date(endTime))
             .signWith(SignatureAlgorithm.HS256,"aaaa")
            .compact();

    return token;
}

public  static  Map<String,Object> getClaimsFromToken(String token){
    Map<String,Object> map=new HashMap<>();

        Jwts.parser()
                .setSigningKey("aaaa")
                .parseClaimsJws(token)
                .getBody();
        map.put("",200);

    return map;
}

}
