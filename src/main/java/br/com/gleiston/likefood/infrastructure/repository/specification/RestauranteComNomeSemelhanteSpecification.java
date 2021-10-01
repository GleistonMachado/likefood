package br.com.gleiston.likefood.infrastructure.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.gleiston.likefood.domain.model.Restaurante;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpecification implements Specification<Restaurante>{

	private static final long serialVersionUID = 1L;
	
	private String nome;

	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}

}
