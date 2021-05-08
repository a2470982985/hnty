package com.coco360.pojo;

public class RespMsg {
    private int code;
    private String msg;
    private String data;

    public RespMsg(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public RespMsg() {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
