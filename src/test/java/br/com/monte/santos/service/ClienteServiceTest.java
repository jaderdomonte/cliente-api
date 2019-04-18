package br.com.monte.santos.service;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.monte.santos.dto.LocalizacaoDTO;
import br.com.monte.santos.facade.ClimaFacade;
import br.com.monte.santos.facade.GeolocalizacaoFacade;
import br.com.monte.santos.model.Cliente;
import br.com.monte.santos.repository.ClienteRepository;
import br.com.monte.santos.response.ClimaConsolidadoResponse;
import br.com.monte.santos.response.LocationResponse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteServiceTest {
	
	@InjectMocks
	private ClienteService service;
	
	@Mock
	private ClienteRepository repository;
	
	@Mock
	private GeolocalizacaoFacade geolocalizacaoFacade;
	
	@Mock
	private ClimaFacade climaFacade;
	
	@Test
	public void deveRetornarTodosOsClientes() {
		BDDMockito.when(repository.findAll()).thenReturn(criarListaDeClientes());
		
		service.listarTodos();
		
		verify(repository).findAll();
	}
	
	@Test
	public void deveRetornarClientePorId() {
		Long idCliente = 1L;
		BDDMockito.when(repository.findOne(idCliente)).thenReturn(criarCliente());
		
		service.consultarPorId(idCliente);
		
		verify(repository).findOne(idCliente);
	}
	
	@Test
	public void deveSalvarCliente() {
		Cliente cliente = Cliente.builder().nome("Cliente 1").build();
		
		BDDMockito.when(repository.save(cliente)).thenReturn(criarCliente());
		BDDMockito.when(geolocalizacaoFacade.consultarLocalizacaoPorIp()).thenReturn(criarLocalizacaoDTO());
		BDDMockito.when(geolocalizacaoFacade.consultarLocalizacaoPorIp()).thenReturn(criarLocalizacaoDTO());
		BDDMockito.when(climaFacade.consultarLocalizacaoPorLatitudeLongitude("1", "2")).thenReturn(criarLocationResponse());
		
		service.salvar(cliente);
		
		verify(repository).save(cliente);
	}
	
	@Test
	public void deveAtualizarCliente() {
		Cliente cliente = Cliente.builder().id(1L).nome("Cliente 2").build();
		BDDMockito.when(repository.save(cliente)).thenReturn(criarCliente());
		
		service.atualizar(cliente);
		
		verify(repository).save(cliente);
	}
	
	@Test
	public void deveDeletarCliente() {
		Cliente cliente = criarCliente();
		BDDMockito.doNothing().when(repository).delete(cliente.getId());
		
		service.deletar(cliente.getId());
		
		verify(repository).delete(cliente.getId());
	}

	private LocationResponse criarLocationResponse() {
		return LocationResponse.builder().id(1L).cidade("Cidade 1").climaResponse(criarListaClimaConsolidadoResponse()).build();
	}

	private List<ClimaConsolidadoResponse> criarListaClimaConsolidadoResponse() {
		return Arrays.asList(ClimaConsolidadoResponse.builder().temperaturaMinima(BigDecimal.valueOf(10L)).temperaturaMaxima(BigDecimal.valueOf(30L)).build());
	}

	private LocalizacaoDTO criarLocalizacaoDTO() {
		return LocalizacaoDTO.builder().latitude("1").longitude("2").build();
	}

	private List<Cliente> criarListaDeClientes() {
		return Arrays.asList(Cliente.builder().nome("Cliente 1").build(), Cliente.builder().nome("Cliente 2").build());
	}
	
	private Cliente criarCliente() {
		return Cliente.builder().id(1L).nome("Cliente 1").build();
	} 
}
