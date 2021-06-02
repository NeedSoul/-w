package com.fh.result;

public enum ResponseEnum {
    TOKEN_INVALID(6008,"登录异常请重新登录"),
    SUCCESS(200,"操作成功"),
    ERROR(500,"操作失败"),
    ACCOUNT_NOT_EXIST(6001,"账号不存在"),
    ACCOUNT_INVALID(6002,"无效账号"),
    PASSWORD_ERROR(6003,"密码错误"),
    IS_NULL(6004,"账号或者密码不能为空"),
    PERMISSION_INVALID(6009,"权限不足");
    private Integer code;
    private String msg;
    private ResponseEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

}
