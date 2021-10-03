package br.com.elotech.testeTecnico.resource;

import br.com.elotech.testeTecnico.dto.ContatoDto;
import br.com.elotech.testeTecnico.dto.PessoaDto;
import br.com.elotech.testeTecnico.service.ContatoService;
import br.com.elotech.testeTecnico.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/retorna")
public class LeituraResource {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    ContatoService contatoService;

    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<PessoaDto> retornarPessoaPorId(@PathVariable Long idPessoa) throws ResponseStatusException {
        try {
            PessoaDto pessoa = pessoaService.retornarPessoaPorId(idPessoa);
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/contato/{idContato}")
    public ResponseEntity<ContatoDto> retornarContatoPorId(@PathVariable Long idContato) throws ResponseStatusException {
        try {
            ContatoDto contato = contatoService.retornarContatoPorId(idContato);
            return new ResponseEntity<>(contato, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/pessoa")
    public ResponseEntity<List<PessoaDto>> retornarTodasAsPessoas() throws ResponseStatusException {
        try {
            List<PessoaDto> pessoas = pessoaService.retornarTodasAsPessoas();
            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/contato")
    public ResponseEntity<List<ContatoDto>> retornarTodasOsContatos() {
        try {
            List<ContatoDto> contatos = contatoService.retornarTodosOsContatos();
            return new ResponseEntity<>(contatos, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
