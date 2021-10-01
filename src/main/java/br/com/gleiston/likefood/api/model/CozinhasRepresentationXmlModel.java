package br.com.gleiston.likefood.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import br.com.gleiston.likefood.domain.model.Cozinha;
import lombok.Data;
import lombok.NonNull;

@Data
@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasRepresentationXmlModel {
	
	@NonNull
	@JsonProperty("cozinha")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cozinha> cozinhas;
}


// @Data - Gera (Getters / Setters / RequiredArgsConstructor / ToString / EqualsAndHashCode)

// @JacksonXmlRootElement(localName = "cozinhas") - altera o nome da tag pai do elemento xml

// @NonNull - Diz para o lombok @Data que essa é uma propriedade obrigatoria assim vai criar um construtor

// @JacksonXmlElementWrapper(useWrapping = false) - Desabilita o embrulho padrao