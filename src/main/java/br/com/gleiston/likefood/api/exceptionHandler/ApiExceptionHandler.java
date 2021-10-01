package br.com.gleiston.likefood.api.exceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.gleiston.likefood.core.validation.ValidacaoException;
import br.com.gleiston.likefood.domain.exception.EntidadeEmUsoException;
import br.com.gleiston.likefood.domain.exception.EntidadeNaoEncontradaException;
import br.com.gleiston.likefood.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	
	@ExceptionHandler({ValidacaoException.class})
	public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	public ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		String datail = String.format("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
		
		List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
				.map(objctError -> {
					
					String message = messageSource.getMessage(objctError, LocaleContextHolder.getLocale());
					
					String name = objctError.getObjectName();
					
					if(objctError instanceof FieldError) {
						name = ((FieldError) objctError).getField();
					}
							
					return Problem.Object.builder()
							.name(name)
							.message(message)
							.build();
					
				})
				.collect(Collectors.toList());

		
		
		Problem problem = createProblemBuilder(status, problemType, datail)
				.userMessage("Um ou mais campos estão inválidos. Preencha corretamente e tente novamente.")
				.objects(problemObjects)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
		
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		return handleValidationInternal(ex, ex, headers, status, request);
		
	}
	
	
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> handleOutraException(Exception ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		
		String detail = String.format("Ocorreu um erro inesperado no sitema. "
				+ "Tente novamente e se o problema persistir, entre em contato "
				+ "com o aministrador do sistema.");
		
		// Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
		// fazendo logging) para mostrar a stacktrace no console
		// Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
		// para você durante, especialmente na fase de desenvolvimento
		ex.printStackTrace();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
			
	}
	
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADA;
		
		String detail = String.format("O recurso '%s, que você tentou acessar é inexistente.", ex.getRequestURL());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.", 
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
		
	}
	
	

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// Para o uso do ExceptionUtils.getRootCause(), instale a dependencia apache.commons/commons-lang3
		Throwable rootCause = ExceptionUtils.getRootCause(ex); 	 
																

		// Exception lançada ao tentar enviar dados com formatos não compativeis com as propridades da Entidade
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
			
		// Exception lançada quando a prorpiedade está sendo ignirada ou não existe no modelo de dominio
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
			
		}

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath()); // Concatena os valores que estão dentro reference com um '.'
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. "
					+ "Corrija e informe um valor compatível com o tipo %s.", 
					path, ex.getValue(), ex.getTargetType().getSimpleName());

		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);

	}
	
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, 
			HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		
		String datail = String.format("A propriedade '%s' não existe. "
				+ "Corrija ou remova essa propriedade e tente novamente.", path);
		
		Problem problem = createProblemBuilder(status, problemType, datail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
		
	}
	
	
	

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}
	
	

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}
	
	

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);

	}
	
	

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		// Retorno com a mensagem padrão
		if (body == null) {  
			body = Problem.builder()
					.status(status.value())
					.title(status.getReasonPhrase())
					.build();

		// Retorno com a mensagem customizada
		} else if (body instanceof String) { 
			body = Problem.builder()
					.status(status.value())
					.title((String) body)
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String datail) {
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.datail(datail);

	}
	
	
	// Concatena os valores que estão dentro Reference com um '.'
	private String joinPath(List<Reference> references) {
		return references.stream()
				.map(reference -> reference.getFieldName())
				.collect(Collectors.joining("."));
	}
	
	

//	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//	public ResponseEntity<?> handleHttpMediaTypeNotAcceptableException() {
//		
//		Problema problema = Problema.builder()
//				.dateTime(LocalDateTime.now())
//				.message("O tipo de mídia não é aceito. Obs: Somente application/json")
//				.build();
//		
//		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//				.body(problema);
//	}

	// Agora esta sendo tratado por ResponseEntityExceptionHandler

}
