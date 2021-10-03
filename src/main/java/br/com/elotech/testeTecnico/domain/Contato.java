package br.com.elotech.testeTecnico.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Contatos")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contato {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_contato")
    private Long idContato;

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @Email
    @NotBlank
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Contato() {
    }

    public void setIdContato(Long id) {
        this.idContato = id;
    }


    public Long getIdContato() {
        return idContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
