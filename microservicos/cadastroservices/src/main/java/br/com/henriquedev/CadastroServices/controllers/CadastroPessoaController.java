package br.com.henriquedev.CadastroServices.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.henriquedev.CadastroServices.model.PessoaDto;
import br.com.henriquedev.CadastroServices.services.KafkaServices;
import br.com.henriquedev.CadastroServices.services.ValidationServices;

@RestController
@RequestMapping("/cadastro/pessoa")
@CrossOrigin(origins = "http://localhost:3000")
public class CadastroPessoaController {
    
   @Autowired
    private KafkaServices kafkaServices;

    @Autowired
    private ValidationServices validationServices;
    
    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody PessoaDto pessoaDto) { 
        
        if (!validationServices.validarCPF(pessoaDto.cpf())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cpf Invalido");
        }

        if (!validationServices.validarEmail(pessoaDto.email())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email invalido");
        }



        kafkaServices.sendMensagem(pessoaDto);
        return ResponseEntity.ok().build();
    }
}
