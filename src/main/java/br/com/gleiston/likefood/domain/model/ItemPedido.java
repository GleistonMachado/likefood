package br.com.gleiston.likefood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class ItemPedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;
	
	private BigDecimal precoUnitario;
	
	private BigDecimal precoTotal;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pedido pedido;
	

}