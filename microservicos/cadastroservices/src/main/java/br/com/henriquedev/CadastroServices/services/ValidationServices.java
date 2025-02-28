package br.com.henriquedev.CadastroServices.services;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationServices {
    private final RestTemplate restTemplate = new RestTemplate();  // Instancia o RestTemplate

    public boolean validarCPF(String cpf) {
        String url = "http://validationservices:8083/validate/cpf";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("cpf", cpf);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request, Boolean.class);
            return Boolean.TRUE.equals(response.getBody());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return false;
            } else {
                e.printStackTrace();
                throw new RuntimeException("Erro ao validar CPF", e);
            }
        }
    }

    public boolean validarEmail(String email) {
        String url = "http://validationservices:8083/validate/email";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request, Boolean.class);
            return Boolean.TRUE.equals(response.getBody());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return false;
            } else {
                e.printStackTrace();
                throw new RuntimeException("Erro ao validar Email", e);
            }
        }
    }
}
