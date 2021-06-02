package com.fh.pashopadmin.config;


import javax.servlet.http.HttpServletRequest;

public class TokenCommons {

    public  static  final  String tokenKey="ACCESS_TOKEN";

    public  static  final  Long  token_time =30l;

    public  static  final  String userKey="ACCESS_TOKEN";

    public static String  getTokenKey(String  username ,String  token,HttpServletRequest request){
        String deviceType = getDeviceType(request);

        return tokenKey+":"+username+":"+deviceType+":"+token;
    }

    public static String  getUserKey(String username,String type){


        return userKey+":"+username+":"+type+":";
    }


    public static String  getQueryTokenKey(String  username,HttpServletRequest request){
        String deviceType = getDeviceType(request);
        return tokenKey+":"+username+":"+deviceType+":*";
    }



    public static String getDeviceType(HttpServletRequest request){
        String type = request.getHeader("User-Agent");
        String typeName = "";
        if (type.contains("Android")||type.contains("Linux")) {
            System.out.println("Android移动客户端");
            if (type.contains("MicroMessenger")) {
                typeName = "Android";
            }
        } else if (type.contains("iPhone")) {
            System.out   .println("iPhone移动客户端");
            typeName = "iPhone";
        }  else if(type.contains("Windows")){
            System.out.println("Windows");
            typeName = "Windows";
        }
        return typeName;
    }



}
