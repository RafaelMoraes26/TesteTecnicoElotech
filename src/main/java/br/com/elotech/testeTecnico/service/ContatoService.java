package br.com.elotech.testeTecnico.service;

import br.com.elotech.testeTecnico.domain.Contato;
import br.com.elotech.testeTecnico.domain.Pessoa;
import br.com.elotech.testeTecnico.dto.ContatoDto;
import br.com.elotech.testeTecnico.dto.PessoaDto;
import br.com.elotech.testeTecnico.mapper.ContatoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ContatoService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ContatoMapper contatoMapper;

    public void criarContato(ContatoDto contatoDto, Long idPessoa) throws Exception{
        try {
            Contato c1 = contatoMapper.converterContatoDtoParaContato(contatoDto);
            c1.setPessoa(entityManager.find(Pessoa.class, idPessoa));
            entityManager.persist(c1);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void atualizarContato(ContatoDto contatoDto, Long idContato) throws Exception {
        try {
            Contato contatoAntigo = entityManager.find(Contato.class, idContato);
            Contato contato = contatoMapper.atualizarContatoDtoParaContato(contatoDto, contatoAntigo);
            contato.setIdContato(idContato);
            entityManager.merge(contato);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void deletarContato(Long idContato) throws Exception {
        try {
            entityManager.remove(entityManager.find(Contato.class, idContato));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public ContatoDto retornarContatoPorId(Long idContato) throws Exception {
        try {
            Contato contato = entityManager.find(Contato.class, idContato);
            if(contato == null) throw new Exception("Não existe nenhuma contato com este id.");
            return contatoMapper.converterContatoParaContatoDto(contato);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public List<ContatoDto> retornarTodosOsContatos() throws Exception {
        try {
            List<Contato> contatos = entityManager.createQuery("select c from Contato c").getResultList();
            return contatoMapper.converterListaContatoParaListaContatoDto(contatos);
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }
}
