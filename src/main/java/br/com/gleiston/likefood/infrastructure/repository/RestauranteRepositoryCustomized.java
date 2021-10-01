package br.com.gleiston.likefood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.gleiston.likefood.domain.model.Restaurante;

public interface RestauranteRepositoryCustomized {
	
	List<Restaurante> findCustomized(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	List<Restaurante> findCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> findComFreteGratis(String nome);


}