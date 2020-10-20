package com.crud.bean;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    //返回码  100-正确  200- 错误
    private int code;
    //提示信息
    private String msg;

    //放在浏览器上的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    //请求正确
    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("请求成功");
        return result;
    }

    //请求错误
    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("请求失败");
        return result;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
