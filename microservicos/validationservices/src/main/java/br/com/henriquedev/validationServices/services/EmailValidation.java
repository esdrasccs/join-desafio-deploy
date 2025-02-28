package br.com.henriquedev.validationServices.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class EmailValidation {

    
    public boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }
}
