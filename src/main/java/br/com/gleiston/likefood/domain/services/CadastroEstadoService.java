package br.com.gleiston.likefood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gleiston.likefood.domain.exception.EntidadeEmUsoException;
import br.com.gleiston.likefood.domain.exception.EstadoNaoEncontradoException;
import br.com.gleiston.likefood.domain.model.Estado;
import br.com.gleiston.likefood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String ESTADO_ESTA_EM_USO = "Estado de código %d não pode ser removido, pois está em uso.";
	
	@Autowired
	EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(ESTADO_ESTA_EM_USO, id)
				);
		}
	}
	
	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradoException(id));
	}

}
