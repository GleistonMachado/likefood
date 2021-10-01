package br.com.gleiston.likefood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gleiston.likefood.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

//	public List<Estado> listar();
//	public Estado busca(Long id);
//	public Estado salvar(Estado estado);
//	public void remover(Long id);
	
}
