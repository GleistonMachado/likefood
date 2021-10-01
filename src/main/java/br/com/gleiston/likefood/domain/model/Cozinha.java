package br.com.gleiston.likefood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import br.com.gleiston.likefood.core.validation.Groups.ValidateCozinhaId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@JsonRootName("cozinha")
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {
	
	@Id
	@NotNull(groups = ValidateCozinhaId.class)
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@JsonProperty("titulo")
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();
	

}


// @JsonRootName("cozinha") - Altera o nome da classe na representação xml da api para single resource

// @JsonProperty("titulo")  - modifica o nome da propriedade na representação da api

// @JsonIgnore - Remove a propriedade na representação da api 
// e assim nao occore um loop infinito na serialização/criação quando ha relacionamento bidirecional