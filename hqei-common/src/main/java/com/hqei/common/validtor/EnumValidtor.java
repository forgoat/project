package com.hqei.common.validtor;

import com.hqei.common.annotation.EnumValid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

public class EnumValidtor implements ConstraintValidator<EnumValid,String> {

    private static final Logger logger = LoggerFactory.getLogger(EnumValidtor.class);

    Class<?>[] cls; //枚举类

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        cls = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isBlank(value)){
            return true;
        }
        if (cls.length > 0) {
            for (Class<?> cl : cls) {
                try {
                    if (cl.isEnum()) {
                        //枚举类验证
                        Object[] objs = cl.getEnumConstants();
                        Method method = cl.getMethod("name");
                        for (Object obj : objs) {
                            Object code = method.invoke(obj, null);
                            if (value.equals(code.toString())) {
                                return true;
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
            return false;
        }
        return true;
    }
}
