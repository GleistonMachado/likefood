package br.com.gleiston.likefood.infrastructure.repository.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.gleiston.likefood.domain.model.Restaurante;


public class RestauranteSpecifications {
	
	public static Specification<Restaurante> freteGratis() {
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> contemNome(String nome) {
		return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
	}
}
