package br.com.monte.santos.endpoint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	
	@ApiOperation(value = "Lista todos os clientes")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listarTodos(HttpServletRequest httpServletRequest) {
		List<Cliente> clientes = this.service.listarTodos();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna um Cliente por ID", response = Cliente.class)
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable(value = "cliente")
	public ResponseEntity<?> consultarPorId(@PathVariable("id") Long id) {
		Cliente cliente = this.service.consultarPorId(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> salvar(@RequestBody Cliente cliente) {
		Cliente clienteNovo = this.service.salvar(cliente);
		return new ResponseEntity<>(clienteNovo, HttpStatus.CREATED);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@CacheEvict(allEntries=true, value="cliente", beforeInvocation=false)
	public ResponseEntity<?> atualizar(@RequestBody Cliente cliente) {
		this.service.atualizar(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		this.service.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
} 