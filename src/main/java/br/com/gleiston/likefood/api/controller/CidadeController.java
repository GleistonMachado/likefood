package br.com.gleiston.likefood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gleiston.likefood.domain.exception.EstadoNaoEncontradoException;
import br.com.gleiston.likefood.domain.exception.NegocioException;
import br.com.gleiston.likefood.domain.model.Cidade;
import br.com.gleiston.likefood.domain.repository.CidadeRepository;
import br.com.gleiston.likefood.domain.services.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	public List<Cidade> listar(Cidade cidade) {
		return cidadeRepository.findAll();
	}
	
	
	
	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable("id") Long id) {
		return cadastroCidade.buscarOuFalhar(id);
		
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
		try {
			return cadastroCidade.salvar(cidade);
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	
	}
	
	
	
	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable("id") Long id, @RequestBody @Valid Cidade cidade) {

		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			
			return cadastroCidade.salvar(cidadeAtual);
		
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
	}
	
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("id") Long id) {
		cadastroCidade.excluir(id);
	}

}













