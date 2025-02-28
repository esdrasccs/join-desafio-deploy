package br.com.henriquedev.registroServices.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;    
    

    public PessoaModel(PessoaDTO pessoaDTO) {
        this.id = pessoaDTO.id();
        this.nome = pessoaDTO.nome();
        this.cpf = pessoaDTO.cpf();
        this.email = pessoaDTO.email();
        this.telefone = pessoaDTO.telefone();
    }
}
