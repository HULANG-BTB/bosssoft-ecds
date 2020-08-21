package com.bosssoft.ecds.utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author shkstart
 * @create 2020-08-21 11:27
 */
public class ValidationUtils {
    public static Validator getValidator(){
        return validator;

    }

    static Validator validator;
    static{
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator=validatorFactory.getValidator();
    }
}
