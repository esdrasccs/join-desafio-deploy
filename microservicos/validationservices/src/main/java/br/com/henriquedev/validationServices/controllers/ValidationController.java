package br.com.henriquedev.validationServices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.henriquedev.validationServices.Model.CpfDto;
import br.com.henriquedev.validationServices.Model.EmailDto;
import br.com.henriquedev.validationServices.services.CPFValidation;
import br.com.henriquedev.validationServices.services.EmailValidation;



@RestController
@RequestMapping("/validate")
public class ValidationController {

    @Autowired
    private CPFValidation CPFValidation;

    @Autowired
    private EmailValidation EmailValidation;
    
    @PostMapping("/cpf")
    public ResponseEntity<Boolean> validateCPF(@RequestBody CpfDto cpf) {
        String cpfString = cpf.cpf();
        return CPFValidation.isValidCPF(cpfString) 
            ? ResponseEntity.ok(true)
            : ResponseEntity.badRequest().body(false);
    }

    @PostMapping("/email")
    public ResponseEntity<Boolean>  validateEmail(@RequestBody EmailDto email) {
        String emailString = email.email();
        return EmailValidation.isValidEmail(emailString)
            ? ResponseEntity.ok(true)
            : ResponseEntity.badRequest().body(false);
    }
}
