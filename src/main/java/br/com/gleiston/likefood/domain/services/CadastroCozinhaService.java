package br.com.gleiston.likefood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gleiston.likefood.domain.exception.CozinhaNaoEncontradaException;
import br.com.gleiston.likefood.domain.exception.EntidadeEmUsoException;
import br.com.gleiston.likefood.domain.model.Cozinha;
import br.com.gleiston.likefood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso.";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	
	public void excluir(Long id) {
		
		try {
			cozinhaRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
			 
		} catch (DataIntegrityViolationException e) {  // DataIntegrityViolationException Exception de constraint de associação de tabelas 
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, id)
				);
			
		}
		
	}
	
	public Cozinha buscarOuFalhar(Long id) {
		return cozinhaRepository.findById(id)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}

}
