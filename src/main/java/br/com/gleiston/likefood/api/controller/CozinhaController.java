package br.com.gleiston.likefood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gleiston.likefood.domain.model.Cozinha;
import br.com.gleiston.likefood.domain.repository.CozinhaRepository;
import br.com.gleiston.likefood.domain.services.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas", produces =  MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	
	
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRepository.findAll();
	}
	
	
	
//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public CozinhasRepresentationXmlModel listarXml(){
//		return new CozinhasRepresentationXmlModel(cozinhaRepository.findAll());
//	}
	
	
	
	@GetMapping("/{id}")
	public Cozinha buscar(@PathVariable("id") Long id) {
		return cadastroCozinha.buscarOuFalhar(id);
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody @Valid Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}
	
	
	
	@PutMapping("/{id}")
	public Cozinha atualizar(@PathVariable("id") Long id, @RequestBody @Valid Cozinha cozinha) {
		
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");   // A propriedade id sera ignorado na hora da copia das propriedades, 
															    // assim não acarretara erros pois ela estara nula vindo do cliente,
															   // porque não adicionamos o id no corpo quando enviamos a solicitação.
		return cadastroCozinha.salvar(cozinhaAtual);
		
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("id") Long id) {
		cadastroCozinha.excluir(id);
	}
	
	
}






















