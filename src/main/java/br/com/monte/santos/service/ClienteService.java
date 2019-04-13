package br.com.monte.santos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.monte.santos.dto.LocalizacaoDTO;
import br.com.monte.santos.facade.GeolocalizacaoFacade;
import br.com.monte.santos.model.Cliente;
import br.com.monte.santos.repository.ClienteRepository;
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
	
	public List<Cliente> listarTodos(){
		LocalizacaoDTO consultarLocalizacaoPorIp = geolocalizacaoFacade.consultarLocalizacaoPorIp("201.90.180.103");
		System.out.println("GEOLOCALIZAÇÃO: "+consultarLocalizacaoPorIp);
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
