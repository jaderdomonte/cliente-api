package br.com.monte.santos.service.abstraction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.monte.santos.exceptions.RecursoNaoEncontradoException;

public abstract class Service<T> {
	
	public List<T> listarTodos(){
		return getRepository().findAll();
	}
	
	public T consultarPorId(Long id) {
		T t = (T) getRepository().findOne(id);
		
		if(isRecursoInexistente(t)) {
			throw new RecursoNaoEncontradoException("Recurso não encontrado para o ID " + id);
		}
		
		return t;
	}
	
	private boolean isRecursoInexistente(T t) {
		return t == null;
	}
	
	public T atualizar(T t) {
		return (T) getRepository().save(t);
	}
	
	public T salvar(T t) {
		return (T) getRepository().save(t);
	}
	
	public void deletar(Long id) {
		getRepository().delete(id);
	}

	protected abstract JpaRepository getRepository();
}
