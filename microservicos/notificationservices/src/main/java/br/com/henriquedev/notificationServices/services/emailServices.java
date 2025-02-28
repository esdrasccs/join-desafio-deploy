package br.com.henriquedev.notificationServices.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import br.com.henriquedev.notificationServices.model.emailModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@Component
public class emailServices {
        
    @Autowired
    private final JavaMailSender JavaMailSender;

    public emailServices(JavaMailSender javaMailSender) {
        this.JavaMailSender = javaMailSender;
    }

    /**
     * Método responsável por enviar email atraves do Spring Mail.
     * É retornado uma RuntimeException caso ocorra algum erro de envio.
     * Este metodo recebe como parametro um objeto do tipo emailModel.
     * @param email
     * @return void
     * @throws RuntimeException Erro ao enviar email
     */ 

    public void sendEmail(emailModel email) {
        var message = new SimpleMailMessage();        
        message.setFrom("esdrasccs@gmail.com");    
        message.setTo(email.destinatario());    
        message.setSubject("Confirmação de cadastro");
        message.setText(email.corpoEmail());
        
        try {
            JavaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email");
        }        
    }
    
}
