package br.com.elotech.testeTecnico.repository;

import br.com.elotech.testeTecnico.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositoryCustom extends JpaRepository<Pessoa, Long> {


}
