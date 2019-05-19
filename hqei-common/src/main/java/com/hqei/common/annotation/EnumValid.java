package com.hqei.common.annotation;


import com.hqei.common.validtor.EnumValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidtor.class})
@Documented
public @interface EnumValid {
    String message() default "类型错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};
}

