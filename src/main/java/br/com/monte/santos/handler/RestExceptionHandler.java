package br.com.monte.santos.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.monte.santos.exceptions.RecursoNaoEncontradoError;
import br.com.monte.santos.exceptions.RecursoNaoEncontradoException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(RecursoNaoEncontradoException rneException){
		RecursoNaoEncontradoError rneError = construirRecursoNaoEncontradoError(rneException);

		return new ResponseEntity<>(rneError, HttpStatus.NOT_FOUND);
	}

	private RecursoNaoEncontradoError construirRecursoNaoEncontradoError(RecursoNaoEncontradoException rneException) {
		RecursoNaoEncontradoError rneError = RecursoNaoEncontradoError.builder().build();
		rneError.setData(new Date().getTime());
		rneError.setStatus(HttpStatus.NOT_FOUND.value());
		rneError.setTitulo("Recurso não encontrado");
		rneError.setDetalhe(rneException.getMessage());
		rneError.setMensagem(rneException.getClass().getName());
		return rneError;
	}
	
//	@Override
//	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException, 
//															   HttpHeaders headers, 
//															   HttpStatus status, 
//															   WebRequest request) {
//
//		List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
//		
//		ValidationErrorDetail vedDetails = buildValidationErrorDetail(manvException, fieldErrors);
//
//		return new ResponseEntity<>(vedDetails, HttpStatus.BAD_REQUEST);
//	}
//	
//	private ValidationErrorDetail buildValidationErrorDetail(MethodArgumentNotValidException manvException,
//			List<FieldError> fieldErrors) {
//		ValidationErrorDetail vedDetails = ValidationErrorDetail.builder().build();
//		vedDetails.setTimestamp(new Date().getTime());
//		vedDetails.setStatus(HttpStatus.BAD_REQUEST.value());
//		vedDetails.setTitle("Argument Not Valid");
//		vedDetails.setDetail("Argument Not Valid");
//		vedDetails.setMessage(manvException.getClass().getName());
//		vedDetails.setField(getFieldsError(fieldErrors));
//		vedDetails.setFieldMessage(getFieldsMessageError(fieldErrors));
//		return vedDetails;
//	}
//
//	private String getFieldsMessageError(List<FieldError> fieldErrors) {
//		String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
//		return fieldsMessage;
//	}
//
//	private String getFieldsError(List<FieldError> fieldErrors) {
//		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
//		return fields;
//	}
//	
//	@Override
//	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, 
//														  Object body, 
//														  HttpHeaders headers, 
//														  HttpStatus status, 
//														  WebRequest request) {
//
//		ErrorDetails errorDetails = buildErrorDetails(ex, status);
//		
//		return new ResponseEntity<>(errorDetails, headers, status);
//	}
//
//	private ErrorDetails buildErrorDetails(Exception ex, HttpStatus status) {
//		ErrorDetails errorDetails = new ErrorDetails();
//		errorDetails.setTimestamp(new Date().getTime());
//		errorDetails.setStatus(status.value());
//		errorDetails.setTitle("Internal Exception");
//		errorDetails.setDetail(ex.getMessage());
//		errorDetails.setMessage(ex.getClass().getName());
//		
//		return errorDetails;
//	}
}
