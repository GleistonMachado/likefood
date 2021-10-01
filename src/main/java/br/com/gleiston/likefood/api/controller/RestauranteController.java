package br.com.gleiston.likefood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gleiston.likefood.core.validation.ValidacaoException;
import br.com.gleiston.likefood.domain.exception.CozinhaNaoEncontradaException;
import br.com.gleiston.likefood.domain.exception.NegocioException;
import br.com.gleiston.likefood.domain.model.Restaurante;
import br.com.gleiston.likefood.domain.repository.RestauranteRepository;
import br.com.gleiston.likefood.domain.services.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private SmartValidator validator;

	
	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	
	
	@GetMapping("/{id}")
	public Restaurante buscar(@PathVariable("id") Long id) {
		return cadastroRestaurante.buscarOuFalhar(id);
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
		
		try {
			return  cadastroRestaurante.salvar(restaurante);
			
		} catch (CozinhaNaoEncontradaException  e) {
			throw new NegocioException(e.getMessage());
			
		}
		
	}
	
	
	
	@PutMapping("/{id}")
	public Restaurante atualizar(@PathVariable("id") Long id, @RequestBody @Valid Restaurante restaurante) {
		
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formaPagamentos", "endereco", "dataCadastro", "produtos");
		
		try {
			return cadastroRestaurante.salvar(restauranteAtual);
			
		} catch (CozinhaNaoEncontradaException  e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	
	
	@PatchMapping("/{id}")
	public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody Map<String,
			 Object> campos, HttpServletRequest request) {
		
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);
		
		merge(campos, restauranteAtual, request);
		
		validate(restauranteAtual, "restaurante");
		
		return atualizar(id, restauranteAtual);
		
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteAtual, HttpServletRequest request) {
		
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
	
		
		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			// Determina que se uma propriedade estiver sendo ignorada, laçara um IllegalArgumentException
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			
			// Determina que se não existir uma propriedade laçara um IllegalArgumentException
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			
			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
			
			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				
				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				
				field.setAccessible(true);
				
				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
				
				ReflectionUtils.setField(field, restauranteAtual, novoValor);
				
			});
			
		} catch (IllegalArgumentException ex) {
			
			Throwable rootCouse = ExceptionUtils.getRootCause(ex);
			throw new HttpMessageNotReadableException(ex.getMessage(), rootCouse, serverHttpRequest);
			
		}
		
	}
	
	private void validate(Restaurante restauranteAtual, String objectName) {
		
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restauranteAtual, objectName);
		
		validator.validate(restauranteAtual, bindingResult);
		
		if(bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
		
	}
	


	@DeleteMapping("{id}")
	public void remover(@PathVariable("id") Long id) {
		cadastroRestaurante.excluir(id);
	}
	
	
	
	

}
