package br.com.elotech.testeTecnico.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDto {

    private String nome;
    private String cpf;
    private LocalDate dtNascimento;
    private List<ContatoDto> lsContatos;

    public PessoaDto(String nome, String cpf, LocalDate dtNascimento, List<ContatoDto> lsContatos) {
        this.nome = nome;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.lsContatos = lsContatos;
    }

    public PessoaDto() {
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

    public List<ContatoDto> getLsContatos() {
        return lsContatos;
    }

    public void setLsContatos(List<ContatoDto> lsContatos) {
        this.lsContatos = lsContatos;
    }
}
