package com.articlesystem.Utils;

/**
 * @author 云佳
 * @create 2022-11-30 22:25
 * @往之不谏，来者可追。
 */
public class LayUIJsonResult<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回的具体内容
     */
    private T data;


    public LayUIJsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public LayUIJsonResult() {
    }


    public LayUIJsonResult fail() {
        return new LayUIJsonResult(1, "操作失败", null);
    }

    public LayUIJsonResult fail(String msg) {
        return new LayUIJsonResult(1, msg, null);
    }

    public LayUIJsonResult success() {
        return new LayUIJsonResult(0, "操作成功", null);
    }


    public LayUIJsonResult success(T data) {
        return new LayUIJsonResult(0, "操作成功", data);
    }
}
