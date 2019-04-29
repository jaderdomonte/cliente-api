package br.com.monte.santos.endpoint;

import static br.com.monte.santos.constants.MessageErrorConstants.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.monte.santos.exceptions.RecursoNaoEncontradoException;
import br.com.monte.santos.model.Cliente;
import br.com.monte.santos.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Api(value = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping(path = "v1/clientes")
public class ClienteEndpoint {
	
	@Autowired
	private ClienteService service;
	
	@ApiOperation(value = "Listar todos os Clientes", response = Cliente[].class)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarTodos() {
		List<Cliente> clientes = this.service.listarTodos();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retornar Cliente por ID", response = Cliente.class)
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "cliente")
	public ResponseEntity<?> consultarPorId(@PathVariable("id") Long id) {
		Cliente cliente = null;
		
		try {
			cliente = this.service.consultarPorId(id);
		} catch (RecursoNaoEncontradoException e) {
			throw e;
		}

		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Salvar Cliente", response = Cliente.class)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> salvar(@RequestBody Cliente cliente) {
		Cliente clienteNovo = this.service.salvar(cliente);
		return new ResponseEntity<>(clienteNovo, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Atualizar Cliente")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@CacheEvict(allEntries=true, value="cliente", beforeInvocation=false)
	public ResponseEntity<?> atualizar(@RequestBody Cliente cliente) {
		this.service.atualizar(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "Apagar Cliente")
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@CacheEvict(allEntries=true, value="cliente", beforeInvocation=false)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		try {
			this.service.deletar(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(CLIENTE_NÃO_ENCONTRADO_PARA_O_ID + id);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
} 