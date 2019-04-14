package br.com.monte.santos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.monte.santos.dto.LocalizacaoDTO;
import br.com.monte.santos.facade.ClimaFacade;
import br.com.monte.santos.facade.GeolocalizacaoFacade;
import br.com.monte.santos.model.Cliente;
import br.com.monte.santos.repository.ClienteRepository;
import br.com.monte.santos.response.LocationSearchResponse;
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
		LocalizacaoDTO localizacao = geolocalizacaoFacade.consultarLocalizacaoPorIp("189.40.57.170");
		System.out.println("LocalizacaoDTO: "+localizacao);
//		LocationSearchResponse locationSearch = climaFacade.consultarLocalizacaoPorLatitudeLongitude(localizacao.getLatitude(), localizacao.getLongitude());
//		System.out.println("LocationSearchResponse: "+locationSearch);
		LocationSearchResponse locationSearch = climaFacade.consultarLocalizacaoPorCidade(localizacao.getCidade());
		System.out.println("LocationSearchResponse: "+locationSearch);
		return this.repository.findAll();
	}
	
	public Cliente consultarPorId(Long idCliente) {
		return this.repository.findOne(idCliente);
	}
	
	public Cliente salvar(Cliente cliente) {
		return this.repository.save(cliente);
	}
	
	public void deletar(Long idCliente) {
		this.repository.delete(idCliente);
	}
}
