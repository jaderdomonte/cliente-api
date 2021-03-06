package br.com.monte.santos.facade;

import br.com.monte.santos.constants.RestPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.monte.santos.dto.LocalizacaoDTO;
import br.com.monte.santos.response.IPVigilanteResponse;
import br.com.monte.santos.rest.client.RestClient;

@Component
public class GeolocalizacaoFacade {

	public LocalizacaoDTO consultarLocalizacaoPorIp() {
		ResponseEntity<IPVigilanteResponse> localizacaoResponse = RestClient.getRestTemplateBuilder(RestPaths.HTTPS_IPVIGILANTE_COM)
															.getForEntity("/", IPVigilanteResponse.class);
		return converterParaLocalizacaoDTO(localizacaoResponse);
	}
	
	private LocalizacaoDTO converterParaLocalizacaoDTO(ResponseEntity<IPVigilanteResponse> localizacaoResponse) {
		return LocalizacaoDTO.builder().latitude(localizacaoResponse.getBody().getData().getLatitude())
									   .longitude(localizacaoResponse.getBody().getData().getLongitude())
									   .cidade(localizacaoResponse.getBody().getData().getCidade())
									   .build();
	}
}
