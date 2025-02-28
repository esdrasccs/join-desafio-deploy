package br.com.henriquedev.registroServices.model;

public record PessoaDTO(Long id, String nome, String cpf, String email, String telefone) {

    public PessoaDTO(PessoaDTO pessoaDTO) {
        this(pessoaDTO.id, pessoaDTO.nome, pessoaDTO.cpf, pessoaDTO.email, pessoaDTO.telefone);
    }
}
