package com.hqei.common;

import com.google.common.collect.Maps;
import com.hqei.common.enums.BaseCodeEnum;
import com.hqei.common.exception.SysException;
import com.hqei.common.util.reflect.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 统一处理参数校验失败
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return responseError(BaseCodeEnum.INVALID_PARAMS, bindingResult);
    }

    @ExceptionHandler(BindException.class)
    public BaseResponse bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        return responseError(BaseCodeEnum.INVALID_PARAMS, bindingResult);
    }

    /**
     * 该控制器异常统一处理
     *
     * @param e 异常对象
     * @return
     */
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        logger.error(e.getMessage(), e);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setCode(BaseCodeEnum.SYSTEM_ERROR.getCode());

            if (e instanceof SysException) {
                baseResponse.setMsg(e.getMessage());
            } else {
                baseResponse.setMsg("系统异常，请联系管理员");
            }
            writer.write(baseResponse.toString());
            writer.flush();

        } catch (IOException e1) {
            logger.error(e1.getMessage(), e1);
        }
    }

    protected BaseResponse responseError(Enum enumObj, BindingResult result){
        Map<String, String> errorMap = Maps.newHashMap();
        for (ObjectError error : result.getAllErrors()) {
            if(error instanceof FieldError){
                errorMap.put(new StringBuffer(((FieldError) error).getField()).toString(),error.getDefaultMessage());
                continue;
            }
            errorMap.put(new StringBuffer(error.getObjectName()).toString(),error.getDefaultMessage());
        }
        return new BaseResponse((String) ReflectionUtil.getFieldValue(enumObj, "code"), (String) ReflectionUtil.getFieldValue(enumObj, "msg"), errorMap);
    }

    protected BaseResponse responseError(BaseCodeEnum baseCodeEnum, BindingResult result){
        Map<String, String> errorMap = Maps.newHashMap();
        for (ObjectError error : result.getAllErrors()) {
            if(error instanceof FieldError){
                errorMap.put(new StringBuffer(((FieldError) error).getField()).toString(),error.getDefaultMessage());
                continue;
            }
            errorMap.put(new StringBuffer(error.getObjectName()).toString(),error.getDefaultMessage());
        }
        return new BaseResponse(baseCodeEnum.getCode(), baseCodeEnum.getMsg(), errorMap);
    }

    public <T extends BaseResponse> T responseError(Class<T> t, Enum enumObj, BindingResult result){
        Map<String, String> errorMap = Maps.newHashMap();
        for (ObjectError error : result.getAllErrors()) {
            if(error instanceof FieldError){
                errorMap.put(new StringBuffer(((FieldError) error).getField()).toString(), error.getDefaultMessage());
                continue;
            }
            errorMap.put(new StringBuffer(error.getObjectName()).toString(), error.getDefaultMessage());
        }
        T c = null;
        try {
            c = t.newInstance();
        } catch (Exception e) {
            logger.error("实例化失败:"+e.getMessage(),e);
            return null;
        }
        c.setErrorMap(errorMap);
        c.setCode((String) ReflectionUtil.getFieldValue(enumObj, "code"));
        c.setMsg((String) ReflectionUtil.getFieldValue(enumObj, "msg"));
        return c;
    }
}
