package br.com.gleiston.likefood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import br.com.gleiston.likefood.core.validation.Groups.ValidateEstadoId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidateEstadoId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Estado estado;

}

// @Data - Gera (Getters / Setters / RequiredArgsConstructor / ToString / EqualsAndHashCode)