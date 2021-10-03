package br.com.elotech.testeTecnico.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Pessoa")
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pessoa {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long idPessoa;


    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;


    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dt_nascimento")
    private LocalDate dtNascimento;

    @OneToMany(mappedBy="pessoa", targetEntity= Contato.class, cascade=CascadeType.ALL)
    @NotNull
    @NotEmpty
    private List<Contato> lsContato;

    public Pessoa() {
    }



    public void setIdPessoa(Long id) {
        this.idPessoa = id;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public List<Contato> getLsContatos() {
        return lsContato;
    }

    public void setLsContatos(List<Contato> lsContatoes) {
        this.lsContato = lsContatoes;
    }
}
