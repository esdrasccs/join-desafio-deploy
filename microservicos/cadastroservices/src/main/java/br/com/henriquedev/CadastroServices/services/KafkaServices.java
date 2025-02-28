package br.com.henriquedev.CadastroServices.services;

import br.com.henriquedev.CadastroServices.model.PessoaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServices {

    @Autowired
    private final KafkaTemplate<String, PessoaDto> kafkaPessoaTemplate;

    public KafkaServices(KafkaTemplate<String, PessoaDto> kafkaPessoaTemplate) {
        this.kafkaPessoaTemplate = kafkaPessoaTemplate;
    }

    public void sendMensagem(PessoaDto pessoaDto) {
        kafkaPessoaTemplate.send("CadastroPessoa", new PessoaDto(pessoaDto));
    }
}