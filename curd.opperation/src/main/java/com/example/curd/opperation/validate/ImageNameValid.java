package com.example.curd.opperation.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {

    // error massage
    String message() default "{Invalid Image}";

    // reprasent group of constraint
    Class<?>[] groups() default { };

    // additional information about annotation
    Class<? extends Payload>[] payload() default { };



}
