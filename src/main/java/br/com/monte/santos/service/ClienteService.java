package br.com.monte.santos.service;

import static br.com.monte.santos.constants.MessageErrorConstants.CLIENTE_NÃO_ENCONTRADO_PARA_O_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.monte.santos.dto.LocalizacaoDTO;
import br.com.monte.santos.exceptions.RecursoNaoEncontradoException;
import br.com.monte.santos.facade.ClimaFacade;
import br.com.monte.santos.facade.GeolocalizacaoFacade;
import br.com.monte.santos.model.Cliente;
import br.com.monte.santos.model.Localizacao;
import br.com.monte.santos.repository.ClienteRepository;
import br.com.monte.santos.response.LocationResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private GeolocalizacaoFacade geolocalizacaoFacade;
	
	@Autowired
	private ClimaFacade climaFacade;
	
	public List<Cliente> listarTodos(){
		return this.repository.findAll();
	}
	
	public Cliente consultarPorId(Long idCliente) {
		Cliente cliente = this.repository.findOne(idCliente);
		
		if(isClienteInexistente(cliente)) {
			throw new RecursoNaoEncontradoException(CLIENTE_NÃO_ENCONTRADO_PARA_O_ID + idCliente);
		}
		
		return cliente;
	}
	
	private boolean isClienteInexistente(Cliente cliente) {
		return cliente == null;
	}
	
	public Cliente atualizar(Cliente cliente) {
		return this.repository.save(cliente);
	}
	
	public Cliente salvar(Cliente cliente) {
		LocalizacaoDTO localizacao = consultarLocalizacaoPorIP();
		LocationResponse locationResponse = consultarClimaPorLocalizacao(localizacao);
		Localizacao localizacaoCliente = construirLocalizacaoCliente(locationResponse);
		cliente.setLocalizacao(localizacaoCliente);
		
		return this.repository.save(cliente);
	}

	private Localizacao construirLocalizacaoCliente(LocationResponse locationResponse) {
		return Localizacao.builder().idLocalizacaoMundial(locationResponse.getId().toString())
									.cidade(locationResponse.getCidade())
									.temperaturaMinima(locationResponse.getClimaResponse().get(0).getTemperaturaMinima())
									.temperaturaMaxima(locationResponse.getClimaResponse().get(0).getTemperaturaMaxima())
									.build();
		
	}

	private LocationResponse consultarClimaPorLocalizacao(LocalizacaoDTO localizacao) {
		return climaFacade.consultarLocalizacaoPorLatitudeLongitude(localizacao.getLatitude(), localizacao.getLongitude());
	}

	private LocalizacaoDTO consultarLocalizacaoPorIP() {
		return geolocalizacaoFacade.consultarLocalizacaoPorIp();
	}
	
	public void deletar(Long idCliente) {
		this.repository.delete(idCliente);
	}
}
