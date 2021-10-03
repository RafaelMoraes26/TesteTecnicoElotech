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
public class ContatoMapper {

    public Contato converterContatoDtoParaContato(ContatoDto contatoDto) {
        Contato contato = new Contato();
        contato.setNome(contatoDto.getNome());
        contato.setEmail(contatoDto.getEmail());
        contato.setTelefone(contatoDto.getTelefone());
        return contato;
    }

    public ContatoDto converterContatoParaContatoDto(Contato contato) {
        ContatoDto contatoDto = new ContatoDto();
        contatoDto.setNome(contato.getNome());
        contatoDto.setTelefone(contato.getTelefone());
        contatoDto.setEmail(contato.getEmail());
        return contatoDto;
    }

    public List<ContatoDto> converterListaContatoParaListaContatoDto(List<Contato> contato) {
        List<ContatoDto> contatoDtos = new ArrayList<>();
        contato.parallelStream().forEach(c1 -> {
            ContatoDto contatoDto = new ContatoDto();
            contatoDto.setNome(c1.getNome());
            contatoDto.setEmail(c1.getEmail());
            contatoDto.setTelefone(c1.getTelefone());
            contatoDtos.add(contatoDto);
        });
        return contatoDtos;
    }

    public List<Contato> converterListaContatoDtoParaListaContato(List<ContatoDto> lsContatoDto, Pessoa pessoa) {
        List<Contato> contatos = new ArrayList<>();
        lsContatoDto.parallelStream().forEach(c1 -> {
            Contato contato = new Contato();
            contato.setNome(c1.getNome());
            contato.setEmail(c1.getEmail());
            contato.setTelefone(c1.getTelefone());
            contato.setPessoa(pessoa);
            contatos.add(contato);
        });
        return contatos;
    }

    public Contato atualizarContatoDtoParaContato(ContatoDto contatoDto, Contato contato) {
        Contato c1 = new Contato();
        c1.setNome(contatoDto.getNome() == null || contatoDto.getNome().equals("") ? contato.getNome() : contatoDto.getNome());
        c1.setEmail(contatoDto.getEmail() == null || contatoDto.getEmail().equals("") ? contato.getEmail() : contatoDto.getEmail());
        c1.setTelefone(contatoDto.getTelefone() == null || contatoDto.getTelefone().equals("") ? contato.getTelefone() : contatoDto.getTelefone());
        c1.setPessoa(contato.getPessoa());
        c1.getPessoa().setIdPessoa(contato.getPessoa().getIdPessoa());
        return c1;
    }
}
