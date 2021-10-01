package br.com.gleiston.likefood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gleiston.likefood.domain.exception.RestauranteNaoEncontradoException;
import br.com.gleiston.likefood.domain.model.Cozinha;
import br.com.gleiston.likefood.domain.model.Restaurante;
import br.com.gleiston.likefood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	
	public Restaurante salvar(Restaurante restaurante) {
		
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
		
	}
	
	
	
	public void excluir(Long id) {
		
		try {
			restauranteRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(id);
			
		} 
		
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
			.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}
	
	

}
