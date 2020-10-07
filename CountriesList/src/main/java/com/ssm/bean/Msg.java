package com.ssm.bean;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    //正确代码为 1xx  错误代码2xx
    private Integer code;

    //页面信息
    private String msg;



    //把要渲染view的数据放入当中
    private Map<String,Object> data = new HashMap<String, Object>();

    public Msg add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    public static Msg fail(){
        Msg msg = new Msg();
        return new Msg(100);
    }

    public static Msg error(){
        return new Msg(200);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Msg(Integer code) {
        this.code = code;
    }

    public Msg() {
    }
}
