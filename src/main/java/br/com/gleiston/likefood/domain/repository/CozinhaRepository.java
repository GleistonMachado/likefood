package br.com.gleiston.likefood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gleiston.likefood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{
	
	// Os methodos abaixo estão sendo usados no Controller de Teste
	List<Cozinha> nome(String nome); 
	List<Cozinha> findCozinhasByNome(String nome);
	
}
