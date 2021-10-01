package br.com.gleiston.likefood.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gleiston.likefood.domain.exception.CidadeNaoEncontradaException;
import br.com.gleiston.likefood.domain.exception.EntidadeEmUsoException;
import br.com.gleiston.likefood.domain.model.Cidade;
import br.com.gleiston.likefood.domain.model.Estado;
import br.com.gleiston.likefood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService{
	
	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso.";
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
	
	
	public void excluir(Long id) {
		
		try {
			cidadeRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, id)
				);
		}
		
		
	}
	
	public Cidade buscarOuFalhar(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow(() -> new CidadeNaoEncontradaException(id));
		
	}
	
}













