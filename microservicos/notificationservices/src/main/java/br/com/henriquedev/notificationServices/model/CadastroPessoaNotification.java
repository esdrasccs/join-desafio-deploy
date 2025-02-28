package br.com.henriquedev.notificationServices.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CadastroPessoaNotification {
    private String nome;
    private String email;
}