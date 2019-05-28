package br.com.monte.santos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
public class ClienteService extends br.com.monte.santos.service.abstraction.Service<Cliente> {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private GeolocalizacaoFacade geolocalizacaoFacade;
	
	@Autowired
	private ClimaFacade climaFacade;
	
	@Override
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
	
	public JpaRepository<Cliente, Long> getRepository() {
		return repository;
	}
}
