package com.hqei.common;

import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.common.util.json.JsonUtil;
import com.hqei.common.util.reflect.ReflectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Map;

/**
 * @Auther: zhangchen
 * @Date: 2019/5/8/0008 15:10
 * @Description:
 */
public class BaseResponse<T> implements Serializable{
    public static final BaseResponse ERROR = new BaseResponse(BaseCodeEnum.FAILURE);
    public static final BaseResponse SUCCESS = new BaseResponse(BaseCodeEnum.SUCCESS);
    private static final Logger logger = LoggerFactory.getLogger(BaseResponse.class);
    private static final long serialVersionUID = 2903429340928350835l;
    private String code;
    private String msg;
    private Map<String, String> errorMap;
    private T result;

    public BaseResponse() {
    }

    public BaseResponse(String code, String msg){
        super();
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String code, String msg, T result){
        super();
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public BaseResponse(BaseCodeEnum baseCodeEnum){
        super();
        this.code = baseCodeEnum.getCode();
        this.msg = baseCodeEnum.getMsg();
    }

    public BaseResponse(BaseCodeEnum baseCodeEnum, T result){
        super();
        this.code = baseCodeEnum.getCode();
        this.msg = baseCodeEnum.getMsg();
        this.result = result;
    }

    public BaseResponse(Enum enumObj){
        super();
        this.code = (String) ReflectionUtil.getFieldValue(enumObj, "code");
        this.msg = (String) ReflectionUtil.getFieldValue(enumObj, "msg");
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    @Transient
    public boolean isSuccess(){
        return null == this? false : this.getCode().equals(BaseCodeEnum.SUCCESS.getCode());
    }

    @Override
    public String toString() {
        String json;
        if(null != result && !(result instanceof String)){
            return new BaseResponse<String>(code, msg, JsonUtil.getDefault().toJson(result)).toString();
        }
        json = JsonUtil.getDefault().toJson(this);
        return StringUtils.isBlank(json) ? BaseResponse.ERROR.toString() : json;
    }
}
