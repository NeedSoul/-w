package com.fh.result;

public class ResultObject {

    private Integer code;
    private String msg;
    private Object data;

    private ResultObject(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    private ResultObject(Integer code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public static ResultObject success(){
        return new ResultObject(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMsg());
    }

    public static ResultObject success(Object data){
        return new ResultObject(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMsg(),data);
    }
    public static ResultObject success(ResponseEnum responseEnum){
        return new ResultObject(responseEnum.getCode(),responseEnum.getMsg());
    }
    public static ResultObject success(ResponseEnum responseEnum,Object data){
        return new ResultObject(responseEnum.getCode(),responseEnum.getMsg(),data);
    }


    public static ResultObject error(){
        return new ResultObject(ResponseEnum.ERROR.getCode(),ResponseEnum.ERROR.getMsg());
    }

    public static ResultObject error(Object data){
        return new ResultObject(ResponseEnum.ERROR.getCode(),ResponseEnum.ERROR.getMsg(),data);
    }
    public static ResultObject error(ResponseEnum responseEnum){
        return new ResultObject(responseEnum.getCode(),responseEnum.getMsg());
    }
    public static ResultObject error(ResponseEnum responseEnum,Object data){
        return new ResultObject(responseEnum.getCode(),responseEnum.getMsg(),data);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
