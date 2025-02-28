package br.com.henriquedev.registroServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import br.com.henriquedev.registroServices.model.PessoaDTO;
import br.com.henriquedev.registroServices.model.PessoaModel;
import br.com.henriquedev.registroServices.repository.pessoaRepository;

@Service
public class kafkaListner {

    @Autowired
    private pessoaRepository pessoaRepository;

    @Autowired
    private KafkaTemplate<String, PessoaDTO> kafkaTemplate;

    @KafkaListener(topics = "CadastroPessoa", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(PessoaDTO message) {           
        try {
            pessoaRepository.save(new PessoaModel(message));
            kafkaTemplate.send("CadastroPessoaNotification", message);          
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage()); 
            e.printStackTrace();
            throw new RuntimeException(e);
        }              

    }
}