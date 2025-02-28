package br.com.henriquedev.notificationServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.stereotype.Service;
import br.com.henriquedev.notificationServices.model.CadastroPessoaNotification;
import br.com.henriquedev.notificationServices.model.emailModel;

@Service
public class kafkaListner {

    @Autowired
    private emailServices emailServices;

    /**
     * Método que consome a mensagem do tópico "CadastroPessoaNotification" e envia um email para o usuário.
     * @param message
     * @throws RuntimeException
     */
    @KafkaListener(topics = "CadastroPessoaNotification", groupId = "${spring.kafka.consumer.group-id}")
    @RetryableTopic(
            attempts = "3",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
            dltTopicSuffix = "-dlt"
    )
    public void consume(CadastroPessoaNotification message) {
        String nome = message.getNome();
        String email = message.getEmail();

        String corpoEmail = "Olá Sr(a)," + nome + ",\n\n" +
                "Seu cadastro foi realizado com sucesso!\n\n" +
                "Atenciosamente,\n" +
                "Equipe henriqueDev";

        emailModel menssage = new emailModel(email, corpoEmail);

        try {
            emailServices.sendEmail(menssage);
        } catch (RuntimeException e) {
            throw e;
        }
        
    }
}