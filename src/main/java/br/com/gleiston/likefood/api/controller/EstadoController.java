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

import br.com.gleiston.likefood.domain.model.Estado;
import br.com.gleiston.likefood.domain.repository.EstadoRepository;
import br.com.gleiston.likefood.domain.services.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;

	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	
	
	@GetMapping("/{id}")
	public Estado buscar(@PathVariable("id") Long id) {
		return cadastroEstado.buscarOuFalhar(id);
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody @Valid Estado estado) {
		return cadastroEstado.salvar(estado);
	}
	
	
	
	@PutMapping("/{id}")
	public Estado atualizar(@PathVariable("id") Long id, @RequestBody @Valid Estado estado) {
		
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(id);
			
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		
		return  cadastroEstado.salvar(estadoAtual);
		
	}
	
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("id") Long id) {
		cadastroEstado.excluir(id);
	}
	
	
}




















