package com.shareniu.v2.utils;


/**
 * @author 分享牛
 */
public class RestMessgae {

    private String message;
    private String code;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static RestMessgae success(String message, Object data){
        RestMessgae restMessgae = new RestMessgae();
        restMessgae.setCode("200");
        restMessgae.setMessage(message);
        restMessgae.setData(data);
        return restMessgae;
    }

    public static RestMessgae fail(String message, Object data){
        RestMessgae restMessgae = new RestMessgae();
        restMessgae.setCode("500");
        restMessgae.setMessage(message);
        restMessgae.setData(data);
        return restMessgae;
    }
}
