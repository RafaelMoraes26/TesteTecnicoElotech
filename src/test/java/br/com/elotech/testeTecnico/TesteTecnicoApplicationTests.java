package br.com.elotech.testeTecnico;

import br.com.elotech.testeTecnico.domain.Contato;
import br.com.elotech.testeTecnico.domain.Pessoa;
import br.com.elotech.testeTecnico.dto.ContatoDto;
import br.com.elotech.testeTecnico.dto.PessoaDto;
import br.com.elotech.testeTecnico.mapper.ContatoMapper;
import br.com.elotech.testeTecnico.mapper.PessoaMapper;
import br.com.elotech.testeTecnico.service.ContatoService;
import br.com.elotech.testeTecnico.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class TesteTecnicoApplicationTests {

	@InjectMocks
	@Spy
	private PessoaService pessoaService = new PessoaService();

	@InjectMocks
	@Spy
	private ContatoService contatoService = new ContatoService();

	@Mock
	private PessoaMapper pessoaMapper;

	@Mock
	private ContatoMapper contatoMapper;

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	private final static String CPF_VALIDO = "17040489740";
	private final static String NOME = "Rafael Gomes";
	private final static LocalDate DT_NASCIMENTO_VALIDA = LocalDate.now().minusDays(1L);
	private final static String TELEFONE_VALIDO = "5521980728858";
	private final static String EMAIL_VALIDO = "rafael@email.com";

	@Test
	void testeConsultarPessoasComSucesso() throws Exception {
		List<PessoaDto> pessoasDto = mapListaPessoaDto();
		List<Pessoa> pessoas = mapListaPessoa();
		when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		when(pessoaMapper.converterListaPessoaParaListaPessoaDto(pessoas)).thenReturn(pessoasDto);
		when(pessoaService.retornarTodasAsPessoas()).thenReturn(pessoasDto);
		assertNotNull(pessoaService.retornarTodasAsPessoas());
	}

	@Test
	void testeConsultarPessoaPorIdComSucesso() throws Exception {
		Pessoa pessoa = mapPessoa();
		PessoaDto pessoaDto = mapPessoaDto();
		when(entityManager.find(Mockito.any(), Mockito.any())).thenReturn(pessoa);
		when(pessoaMapper.converterPessoaParaPessoaDto(pessoa)).thenReturn(pessoaDto);
		when(pessoaService.retornarPessoaPorId(Mockito.anyLong())).thenReturn(pessoaDto);
		assertNotNull(pessoaService.retornarPessoaPorId(Mockito.anyLong()));
	}

	@Test
	void testeConsultarContatoComSucesso() throws Exception {
		List<ContatoDto> contatosDto = mapListaContatoDto();
		List<Contato> contatos = mapListaContato();
		when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		when(contatoMapper.converterListaContatoParaListaContatoDto(contatos)).thenReturn(contatosDto);
		when(contatoService.retornarTodosOsContatos()).thenReturn(contatosDto);
		assertNotNull(contatoService.retornarTodosOsContatos());
	}

	@Test
	void testeConsultarContatoPorIdComSucesso() throws Exception {
		Contato contato = mapContato();
		ContatoDto contatoDto = mapContatoDto();
		when(entityManager.find(Mockito.any(), Mockito.any())).thenReturn(contato);
		when(contatoMapper.converterContatoParaContatoDto(contato)).thenReturn(contatoDto);
		when(contatoService.retornarContatoPorId(Mockito.anyLong())).thenReturn(contatoDto);
		assertNotNull(contatoService.retornarContatoPorId(Mockito.anyLong()));
	}

	@Test
	void deletarPessoa() throws Exception{
		Pessoa pessoa = new Pessoa();
		entityManager.persist(pessoa);
		entityManager.clear();
		Pessoa p1 = entityManager.find(Pessoa.class, pessoa.getIdPessoa());
		when(entityManager.find(Mockito.any(), Mockito.any())).thenReturn(pessoa);
		assertDoesNotThrow(() -> pessoaService.deletarPessoa(pessoa.getIdPessoa()));
	}

	@Test
	void deletarContato() throws Exception{
		Contato contato = new Contato();
		entityManager.persist(contato);
		entityManager.clear();
		Contato c1 = entityManager.find(Contato.class, contato.getIdContato());
		when(entityManager.find(Mockito.any(), Mockito.any())).thenReturn(contato);
		assertDoesNotThrow(() -> contatoService.deletarContato(contato.getIdContato()));
	}

	@Test
	void adicionarPessoa() throws Exception {
		Pessoa pessoa = this.mapPessoa();
		PessoaDto pessoaDto = this.mapPessoaDto();
		when(pessoaMapper.converterPessoaDtoParaPessoa(pessoaDto)).thenReturn(pessoa);
		assertDoesNotThrow(() -> pessoaService.criarPessoa(pessoaDto));
	}

	@Test
	void adicionarContato() throws Exception {
		Contato contato = this.mapContato();
		ContatoDto contatoDto = this.mapContatoDto();
		when(contatoMapper.converterContatoDtoParaContato(contatoDto)).thenReturn(contato);
		assertDoesNotThrow(() -> contatoService.criarContato(contatoDto, 1L));
	}

	@Test
	void atualizarPessoa() throws Exception {
		Pessoa pessoaAntiga = mapPessoa();
		PessoaDto pessoaNova = mapPessoaDto();
		Pessoa nova = mapPessoa();
		when(entityManager.find(Mockito.any(), Mockito.anyLong())).thenReturn(pessoaAntiga);
		when(pessoaMapper.atualizarPessoaDtoParaPessoa(pessoaNova, pessoaAntiga)).thenReturn(nova);
		assertDoesNotThrow(() -> pessoaService.atualizarPessoa(pessoaNova, pessoaAntiga.getIdPessoa()));
	}

	@Test
	void atualizarContato() throws Exception {
		Contato contatoAntigo = mapContato();
		ContatoDto contatoNovo = mapContatoDto();
		Contato novo = mapContato();
		when(entityManager.find(Mockito.any(), Mockito.anyLong())).thenReturn(contatoAntigo);
		when(contatoMapper.atualizarContatoDtoParaContato(contatoNovo, contatoAntigo)).thenReturn(novo);
		assertDoesNotThrow(() -> contatoService.atualizarContato(contatoNovo, contatoAntigo.getIdContato()));
	}

	private List<Pessoa> mapListaPessoa() {
		List<Pessoa> pessoas = new ArrayList<>();
		pessoas.add(this.mapPessoa());
		return pessoas;
	}

	private Pessoa mapPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setIdPessoa(1L);
		pessoa.setCpf(CPF_VALIDO);
		pessoa.setNome(NOME);
		pessoa.setDtNascimento(DT_NASCIMENTO_VALIDA);
		pessoa.setLsContatos(this.mapListaContato());
		return pessoa;
	}

	private List<Contato> mapListaContato() {
		List<Contato> contatos = new ArrayList<>();
		contatos.add(this.mapContato());
		return contatos;
	}

	private Contato mapContato() {
		Contato contato = new Contato();
		contato.setIdContato(1L);
		contato.setEmail(EMAIL_VALIDO);
		contato.setTelefone(TELEFONE_VALIDO);
		contato.setNome(NOME);
		return contato;
	}

	private List<PessoaDto> mapListaPessoaDto() {
		List<PessoaDto> pessoaDtos =  new ArrayList<>();
		pessoaDtos.add(this.mapPessoaDto());
		return pessoaDtos;
	}

	private PessoaDto mapPessoaDto() {
		PessoaDto pessoaDto = new PessoaDto();
		pessoaDto.setCpf(CPF_VALIDO);
		pessoaDto.setNome(NOME);
		pessoaDto.setDtNascimento(DT_NASCIMENTO_VALIDA);
		pessoaDto.setLsContatos(this.mapListaContatoDto());
		return pessoaDto;
	}

	private List<ContatoDto> mapListaContatoDto() {
		List<ContatoDto> contatoDtos = new ArrayList<>();
		contatoDtos.add(mapContatoDto());
		return contatoDtos;
	}

	private ContatoDto mapContatoDto() {
		ContatoDto contatoDto = new ContatoDto();
		contatoDto.setTelefone(TELEFONE_VALIDO);
		contatoDto.setEmail(EMAIL_VALIDO);
		contatoDto.setNome(NOME);
		return contatoDto;
	}

}
