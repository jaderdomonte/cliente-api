package br.com.monte.santos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 3586206803292749586L;

	public RecursoNaoEncontradoException(String message) {
		super(message);
	}
}
