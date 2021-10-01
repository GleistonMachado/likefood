package br.com.gleiston.likefood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.com.gleiston.likefood.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

	private EntityManager manager;

	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiroCadastroCom() {
		
		var jpql = "from " + getDomainClass().getName(); // Pega o nome da classe da entidade
		
		T entity = manager.createQuery(jpql, getDomainClass()) // getDomainClass() é a mesma coisa que Ex: Restaurante.class
			.setMaxResults(1)
			.getSingleResult();
		
		return Optional.ofNullable(entity);
	}

}
