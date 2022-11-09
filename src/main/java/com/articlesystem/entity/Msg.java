package com.articlesystem.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 云佳
 * @create 2022-10-17 23:16
 * @只管耕耘，莫问收获。
 */
public class Msg {
    private String code;
    private String msg;
    public String getCode() {
        return code;
    }
    public static Msg success() {
        Msg result = new Msg();
        result.setCode("200");
        result.setMsg("处理成功");
        return result;
    }
    public static Msg fail() {
        Msg result = new Msg();
        result.setCode("100");
        result.setMsg("处理失败");
        return result;
    }
    public Msg add(String key,Object value) {
        this.getExtend().put(key, value);
        return this;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Map<String, Object> getExtend() {
        return extend;
    }
    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
    private Map<String, Object> extend = new HashMap<String, Object>();
}
