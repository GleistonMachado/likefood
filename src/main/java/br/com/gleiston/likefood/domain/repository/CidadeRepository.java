package br.com.gleiston.likefood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gleiston.likefood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
//	List<Cidade> listar();
//	Cidade buscar(Long id);
//	Cidade salvar(Cidade cidade);
//	void remover(Long id);

}
