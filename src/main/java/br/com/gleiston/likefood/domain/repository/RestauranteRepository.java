package br.com.gleiston.likefood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gleiston.likefood.domain.model.Restaurante;
import br.com.gleiston.likefood.infrastructure.repository.RestauranteRepositoryCustomized;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryCustomized,
		JpaSpecificationExecutor<Restaurante> {
	
	@Query("from Restaurante r join fetch r.cozinha left join fetch r.formaPagamentos")
	List<Restaurante> findAll();

	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> findByNome(String nome, @Param("id") Long cozinhaId);

	// Usando o arquivo orm.xml
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
	
	
	
	
	
//	List<Restaurante> findByNomeContaining(String nome);
//	
//	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
//	
//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

}
