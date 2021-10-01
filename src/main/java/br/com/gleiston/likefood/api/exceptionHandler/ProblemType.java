package br.com.gleiston.likefood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	RECURSO_NAO_ENCONTRADA("/recurso-nao-encontrado", "Recurso não encontrado"),
	
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem incompreensível"),
	
	PARAMETRO_INVALIDO("/parametro-invalido", "Parametro inválido"),
	
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	
	DADOS_INVALIDOS("/dados-ivalidos", "Dados inválidos");
	
	private static final String HTTPS_LIKFOOD = "https://likfood.com.br";

	private String uri;
	
	private String title;

	private ProblemType(String path, String title) {
		this.uri = HTTPS_LIKFOOD + path;
		this.title = title;
	}
	
	
}
