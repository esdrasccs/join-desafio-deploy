package br.com.henriquedev.CadastroServices.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.henriquedev.CadastroServices.model.PessoaDto;
import br.com.henriquedev.CadastroServices.services.KafkaServices;

@RestController
@RequestMapping("/cadastro/pessoa")
public class CadastroPessoaController {
    
   @Autowired
    private KafkaServices kafkaServices;
    
    @PostMapping
    public ResponseEntity<?> postMethodName(@RequestBody PessoaDto pessoaDto) {    
        kafkaServices.sendMensagem(pessoaDto);
        return ResponseEntity.ok().build();
    }
}
