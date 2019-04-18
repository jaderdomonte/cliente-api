package br.com.monte.santos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class RecursoNaoEncontradoError {

	protected String titulo;
	
	protected Integer status;
	
	protected String detalhe;
	
	protected Long data;
	
	protected String mensagem;
}
