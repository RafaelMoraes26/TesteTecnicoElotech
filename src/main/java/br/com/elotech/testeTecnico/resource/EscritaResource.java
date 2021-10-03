package br.com.elotech.testeTecnico.resource;

import br.com.elotech.testeTecnico.dto.ContatoDto;
import br.com.elotech.testeTecnico.dto.PessoaDto;
import br.com.elotech.testeTecnico.service.ContatoService;
import br.com.elotech.testeTecnico.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EscritaResource {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    ContatoService contatoService;

    @Transactional
    @PostMapping("/adicionar/pessoa")
    public ResponseEntity<PessoaDto> criarPessoa(@RequestBody @Valid PessoaDto pessoaDto) throws ResponseStatusException {
        try {
            pessoaService.criarPessoa(pessoaDto);
            return new ResponseEntity<>(pessoaDto, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }

    @Transactional
    @PostMapping("/adicionar/{idPessoa}/contato")
    public ResponseEntity<ContatoDto> criarContato(@PathVariable Long idPessoa, @RequestBody @Valid ContatoDto contatoDto) throws ResponseStatusException {
        try {
            contatoService.criarContato(contatoDto, idPessoa);
            return new ResponseEntity<>(contatoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }

    @Transactional
    @PutMapping("/atualizar/pessoa/{idPessoa}")
    public ResponseEntity<PessoaDto> atualizarPessoa(@PathVariable Long idPessoa, @RequestBody @Valid PessoaDto pessoaDto) throws ResponseStatusException {
        try {
            pessoaService.atualizarPessoa(pessoaDto, idPessoa);
            return new ResponseEntity<>(pessoaDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }

    @Transactional
    @PutMapping("/atualizar/contato/{idContato}")
    public ResponseEntity<ContatoDto> atualizarContato(@PathVariable Long idContato, @RequestBody @Valid ContatoDto contatoDto) throws ResponseStatusException {
        try {
            contatoService.atualizarContato(contatoDto, idContato);
            return new ResponseEntity<>(contatoDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }

    @Transactional
    @DeleteMapping("/deletar/pessoa/{idPessoa}")
    public void deletarPessoa(@PathVariable Long idPessoa) throws ResponseStatusException {
        try {
            pessoaService.deletarPessoa(idPessoa);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }

    @Transactional
    @DeleteMapping("/deletar/contato/{idContato}")
    public void deletarContato(@PathVariable Long idContato) throws ResponseStatusException {
        try {
            contatoService.deletarContato(idContato);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }

    }

}
