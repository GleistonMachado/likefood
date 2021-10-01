package br.com.gleiston.likefood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.gleiston.likefood.core.validation.Groups;
import br.com.gleiston.likefood.core.validation.TaxaFrete;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@NotBlank(message = "Nome é obrigatório!")
	@Column(length = 40, nullable = false)
	private String nome;
	

	//@Multiplo(numero = 5) // Anotação custumizada
	@NotNull
	@TaxaFrete // Anotação custumizada
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;
	
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.ValidateCozinhaId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false) // Neste caso é opcional
	private Cozinha cozinha;
	
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formaPagamentos = new ArrayList<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos;
	


}

// @PositiveOrZero(message = "{TaxaFrete.invalida}") - {TaxaFrete.invalida} chave criada em messages.properties

// @Valid - É usado para validação em cascata, ou seja: ira validar as propiedades da outra entidade tambem

// @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class) - Converte o default para Groups.CozinhaId.class

// @NotBlank - Determina que a propriedade não podera ser vazia, conter espaços em branco e ser nula

// @NotBlank(message = "Nome é obrigatório!")

// @NotNull - Determina que a propriedade não podera null

// @NotEmpty - Determina que a propriedade não podera ser vazia ou conter valor nula

// @PositiveOrZero - Determina que a propriedade so devera conter valores positivos ou zero

// @JsonIgnoreProperties("hibernateLazyInitializer") - hibernateLazyInitializer propriedade da exeção relacionada ao FetchType.LAZY

// @UpdateTimestamp - Adiciona a hora e data da ultima atualização do cadastro

// @CreationTimestamp - Adiciona a hora e data da criação do cadastro



















