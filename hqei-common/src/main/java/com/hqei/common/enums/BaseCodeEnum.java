package com.hqei.common.enums;

public enum BaseCodeEnum {
    SUCCESS("0", "success!"),
    FAILURE("1", "error!"),
    TIME_OUT("000000000002", "request time out!"),
    SIGN_ERROR("000000000003", "signature is error!"),
    SYSTEM_ERROR("000000000004", "system error!"),
    INVALID_PARAMS("000000000005", "invalid params!");

    private String code;
    private String msg;

    BaseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
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

    @Override
    public String toString() {
        return "{" +
                "code:'" + code + '\'' +
                ", msg:'" + msg + '\'' +
                '}';
    }
}
