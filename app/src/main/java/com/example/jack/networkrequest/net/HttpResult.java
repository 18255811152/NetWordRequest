package com.example.jack.networkrequest.net;

public class HttpResult<T> {
//    [{
//        "success":"",	 --->是否成功
//        "code":"",	 --->状态码
//        "message":"",    --->消息框
//        "version":"", --->版本信息
//        "data":[{"key":"value"}],	--->返回数据
//        "is_native" true // 是否跳转h5
//    }]

    private boolean success;
    private int code;
    private String message;
    private String version;
    private T data;
    private boolean is_native;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isIs_native() {
        return is_native;
    }

    public void setIs_native(boolean is_native) {
        this.is_native = is_native;
    }
}