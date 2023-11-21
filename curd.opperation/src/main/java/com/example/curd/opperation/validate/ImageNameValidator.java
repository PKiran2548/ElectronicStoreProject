package com.example.curd.opperation.validate;

import com.example.curd.opperation.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator <ImageNameValid , String> {

    Logger log = LoggerFactory.getLogger(ImageNameValidator.class);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    log.info("Image validator massage :{} " + value);
        // logic
        if ( value.isBlank()){
            return  false;
        }else {
            return true ;
        }


    }
}
