package br.com.elotech.testeTecnico.mapper;

import br.com.elotech.testeTecnico.domain.Contato;
import br.com.elotech.testeTecnico.domain.Pessoa;
import br.com.elotech.testeTecnico.dto.ContatoDto;
import br.com.elotech.testeTecnico.dto.PessoaDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {



    public Pessoa converterPessoaDtoParaPessoa(PessoaDto pessoaDto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setCpf(pessoaDto.getCpf());
        pessoa.setDtNascimento(pessoaDto.getDtNascimento());
        pessoa.setLsContatos(this.mapListaContatos(pessoaDto.getLsContatos(), pessoa));
        return pessoa;
    }

    public List<Contato> mapListaContatos(List<ContatoDto> contatosDto, Pessoa pessoa) {
        List<Contato> lsContato = new ArrayList<>();
        contatosDto.parallelStream().forEach(c1 -> {
            Contato contato = new Contato();
            contato.setNome(c1.getNome());
            contato.setTelefone(c1.getTelefone());
            contato.setEmail(c1.getEmail());
            contato.setPessoa(pessoa);
            lsContato.add(contato);
        });
        return lsContato;
    }

    public PessoaDto converterPessoaParaPessoaDto(Pessoa pessoa) {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome(pessoa.getNome());
        pessoaDto.setCpf(pessoa.getCpf());
        pessoaDto.setDtNascimento(pessoa.getDtNascimento());
        pessoaDto.setLsContatos(pessoa.getLsContatos().stream().map(p -> {
            ContatoDto contatoDto = new ContatoDto();
            contatoDto.setNome(p.getNome());
            contatoDto.setEmail(p.getEmail());
            contatoDto.setTelefone(p.getTelefone());
            return contatoDto;
        }).collect(Collectors.toList()));
        return pessoaDto;
    }

    public List<PessoaDto> converterListaPessoaParaListaPessoaDto(List<Pessoa> pessoa) {
        List<PessoaDto> pessoaDtos = new ArrayList<>();
            pessoa.parallelStream().forEach(p1 -> {
                PessoaDto pessoaDto = new PessoaDto();
                pessoaDto.setNome(p1.getNome());
                pessoaDto.setCpf(p1.getCpf());
                pessoaDto.setDtNascimento(p1.getDtNascimento());
                pessoaDto.setLsContatos(p1.getLsContatos().stream().map(p -> {
                    ContatoDto contatoDto = new ContatoDto();
                    contatoDto.setNome(p.getNome());
                    contatoDto.setEmail(p.getEmail());
                    contatoDto.setTelefone(p.getTelefone());
                    return contatoDto;
                }).collect(Collectors.toList()));
                pessoaDtos.add(pessoaDto);
            });
        return pessoaDtos;
    }

    public Pessoa atualizarPessoaDtoParaPessoa(PessoaDto pessoaDto, Pessoa pessoa) {
        Pessoa p1 = new Pessoa();
        p1.setNome(pessoaDto.getNome() == null || pessoaDto.getNome().equals("") ? pessoa.getNome() : pessoaDto.getNome());
        p1.setCpf(pessoaDto.getCpf() == null || pessoaDto.getCpf().equals("") ? pessoa.getCpf() : pessoaDto.getCpf());
        p1.setDtNascimento(pessoaDto.getDtNascimento() == null ? pessoa.getDtNascimento() : pessoaDto.getDtNascimento());
        return p1;
    }


}
