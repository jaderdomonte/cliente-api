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
}
