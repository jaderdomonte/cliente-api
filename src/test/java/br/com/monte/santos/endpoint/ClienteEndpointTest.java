package br.com.monte.santos.endpoint;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.monte.santos.model.Cliente;
import br.com.monte.santos.service.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteEndpointTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@MockBean
	private ClienteService service;
	
	@Test
	public void deveRetornarCodigo200AoListarTodosOsClientes() {
		List<Cliente> clientes = criarListaDeClientes();
		BDDMockito.when(service.listarTodos()).thenReturn(clientes);
		
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/clientes/", String.class);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void deveRetornarCodigo200AoConsultarClientePorId() {
		Cliente cliente = criarCliente(1L);
		BDDMockito.when(service.consultarPorId(cliente.getId())).thenReturn(cliente);
		
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/clientes/{id}", String.class, cliente.getId());
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void deveRetornarCodigo404AoConsultarClientePorIdInexistente() {
		Long idCliente = 3L;
		BDDMockito.when(service.consultarPorId(idCliente)).thenReturn(null);
		
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/clientes/{id}", String.class, idCliente);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void deveRetornarCodigo201AoSalvarCliente() {
		Cliente cliente = criarCliente(1L);
		BDDMockito.when(service.salvar(cliente)).thenReturn(cliente);
		
		ResponseEntity<String> response = restTemplate.postForEntity("/v1/clientes/", cliente, String.class); 
		
		assertThat(response.getStatusCodeValue()).isEqualTo(201);
	}

	private Cliente criarCliente(Long id) {
		return Cliente.builder().id(id).nome("Cliente "+id).build();
	} 

	private List<Cliente> criarListaDeClientes() {
		return Arrays.asList(Cliente.builder().id(1L).nome("Cliente 1").build(), Cliente.builder().id(2L).nome("Cliente 2").build());
	}
}
