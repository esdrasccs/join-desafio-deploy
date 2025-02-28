package br.com.henriquedev.CadastroServices.model;

public record PessoaDto(String nome, String cpf, String email, String telefone) {

    public PessoaDto (PessoaDto pessoaDto){
        this(pessoaDto.nome, pessoaDto.cpf, pessoaDto.email, pessoaDto.telefone);
    }
}
