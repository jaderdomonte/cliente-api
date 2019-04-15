package br.com.monte.santos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.monte.santos.dto.LocalizacaoDTO;
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
		return this.repository.findOne(idCliente);
	}
	
	public Cliente salvar(Cliente cliente) {
		return this.repository.save(cliente);
	}
	
	public Cliente salvar(Cliente cliente, String ip) {
		LocalizacaoDTO localizacao = consultarLocalizacaoPorIP(ip);
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

	private LocalizacaoDTO consultarLocalizacaoPorIP(String ip) {
		return geolocalizacaoFacade.consultarLocalizacaoPorIp("189.40.57.170");
	}
	
	public void deletar(Long idCliente) {
		this.repository.delete(idCliente);
	}
}
