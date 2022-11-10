package org.springframework.samples.petclinic.token;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TokenValidator implements Validator {

    // private static final String REQUIRED = "required";

    @Override
    public boolean supports(Class<?> clazz) {
        return Token.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        // TODO Auto-generated method stub
    }

}
