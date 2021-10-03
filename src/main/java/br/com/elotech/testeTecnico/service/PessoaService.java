package br.com.elotech.testeTecnico.service;

import br.com.elotech.testeTecnico.domain.Pessoa;
import br.com.elotech.testeTecnico.dto.PessoaDto;
import br.com.elotech.testeTecnico.mapper.PessoaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaMapper pessoaMapper;

    @Autowired
    EntityManager entityManager;

    public void criarPessoa(PessoaDto pessoaDto) throws Exception {
        Pessoa pessoa = pessoaMapper.converterPessoaDtoParaPessoa(pessoaDto);
        try {
            if(pessoa.getDtNascimento().isBefore(LocalDate.now()) || pessoa.getDtNascimento().isEqual(LocalDate.now()))
                entityManager.persist(pessoa);
            else
                throw new Exception("Entre uma data de nascimento válida.");
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public void atualizarPessoa(PessoaDto pessoaDto, Long idPessoa) throws Exception {
        try {
            Pessoa pessoaAntiga = entityManager.find(Pessoa.class, idPessoa);
            Pessoa pessoa = pessoaMapper.atualizarPessoaDtoParaPessoa(pessoaDto, pessoaAntiga);
            pessoa.setIdPessoa(idPessoa);
            entityManager.merge(pessoa);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void deletarPessoa(Long idPessoa) throws Exception {
        try {
            entityManager.remove(entityManager.find(Pessoa.class, idPessoa));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public PessoaDto retornarPessoaPorId(Long idPessoa) throws Exception {
        try {
            Pessoa pessoa = entityManager.find(Pessoa.class, idPessoa);
            if(pessoa == null) throw new Exception("Não existe nenhuma pessoa com este id.");
            return pessoaMapper.converterPessoaParaPessoaDto(pessoa);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public List<PessoaDto> retornarTodasAsPessoas() throws Exception {
        try {
            List<Pessoa> pessoa = entityManager.createQuery("select p from Pessoa p").getResultList();
            return pessoaMapper.converterListaPessoaParaListaPessoaDto(pessoa);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }
}
