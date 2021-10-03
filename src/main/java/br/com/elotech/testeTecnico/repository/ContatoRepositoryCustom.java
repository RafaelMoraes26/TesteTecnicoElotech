package br.com.elotech.testeTecnico.repository;

import br.com.elotech.testeTecnico.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepositoryCustom extends JpaRepository<Contato, Long> {

}
