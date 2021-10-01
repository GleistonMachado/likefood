package br.com.gleiston.likefood.infrastructure.repository;

import static br.com.gleiston.likefood.infrastructure.repository.specification.RestauranteSpecifications.contemNome;
import static br.com.gleiston.likefood.infrastructure.repository.specification.RestauranteSpecifications.freteGratis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.gleiston.likefood.domain.model.Restaurante;
import br.com.gleiston.likefood.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustomized {

	@PersistenceContext // Injeta um EntityManeger
	private EntityManager manager;
	
	@Autowired @Lazy
	private RestauranteRepository restauranteRepository;
	
	
	// Customizado
	@Override
	public List<Restaurante> findCustomized(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		// Consulta fixa
//		var jpql = "from Restaurante"
//				+ " where nome like :nome"
//				+ " and taxaFrete between :taxaInicial and :taxaFinal";
//		
//		return manager.createQuery(jpql, Restaurante.class)
//				.setParameter("nome", "%" + nome + "%")
//				.setParameter("taxaInicial", taxaFreteInicial)
//				.setParameter("taxaFinal", taxaFreteFinal)
//				.getResultList();
		
		
		// Consulata Dinamica
		var jpql = new StringBuilder();
		jpql.append("from Restaurante where 0 = 0 ");
		
		var parametros = new HashMap<String, Object>();
		
		if(StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		
		if(taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaFreteInicial);
		}
		
		if(taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFreteFinal);
		}
		
		TypedQuery<Restaurante> consulta = manager.createQuery(jpql.toString(), Restaurante.class);
	
		parametros.forEach((chave, valor) -> consulta.setParameter(chave, valor));
		
		return consulta.getResultList();
		
	}
	
	
	// Usando o Criteria
	@Override
	public List<Restaurante> findCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder(); // FÁBRICA
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		
		Root<Restaurante> root = criteria.from(Restaurante.class); // from Restaurante
		
		var predicates = new ArrayList<Predicate>();
		
		if(StringUtils.hasText(nome)) {
			predicates.add(
					builder.like(
							root.get("nome"), "%" + nome + "%")
					);
		}
		
		if(taxaFreteInicial != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(
							root.get("taxaFrete"), taxaFreteInicial)
					);
		}
		
		if(taxaFreteFinal != null) {
			predicates.add(
					builder.lessThanOrEqualTo(
							root.get("taxaFrete"), taxaFreteFinal)
					);
		}
		
		
		criteria.where(predicates.toArray(new Predicate[0]));  // toArray(new Predicate[0]) -> Transforma um Array List em um Array comum
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}


	// Usando o Specification com @lazy
	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(freteGratis().and(contemNome(nome)));
	}
	
	


}
