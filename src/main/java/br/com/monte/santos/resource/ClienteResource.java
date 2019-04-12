package br.com.monte.santos.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping(path = "v1/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<?> listarTodos() {
		List<Cliente> clientes = this.service.listarTodos();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> consultarPorId(@PathVariable("id") Long id) {
		Cliente cliente = this.service.consultarPorId(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Cliente cliente) {
		Cliente clienteNovo = salvarOuAtualizar(cliente);
		return new ResponseEntity<>(clienteNovo, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Cliente cliente) {
		salvarOuAtualizar(cliente);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		this.service.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private Cliente salvarOuAtualizar(Cliente cliente) {
		return this.service.salvar(cliente);
	}
} 